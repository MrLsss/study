//package com.study.java.collection.map;
//
//import com.study.java.collection.interfaces.MyMap;
//
//import java.util.*;
//
///**
// * Copyright: Copyright (c) 2021
// *
// * <p> Description:
// *
// * @author liushuai
// * @version 1.0.0
// * @createTime 2021年03月17日 10:46
// */
//public class MyHashMap<K, V> implements MyMap<K, V> {
//
//    Map<String, String> map = new HashMap<>();
//
//    /**
//     * 默认初始容量
//     * 必须是2的次幂
//     */
//    static final int DEFAULT_INIT_CAPACITY = 16;
//
//    /**
//     * 最大容量
//     * 如果构造函数隐式的使用了更高的值，必须是2的次幂，并且小于等于1 << 30
//     */
//    static final int MAX_CAPACITY = 1 << 30;
//
//    /**
//     * 默认负载因子
//     * 默认的加载因子是0.75，默认初始容量是16，因此可以得出HashMap的默认容量是：0.75*16=12
//     * 如果是0.5的话，每次达到容量的一半就要扩容，默认容量是16，达到8就扩容成32，达到16就扩容，到后来使用的空间和未使用的差额会越来越大，空间利用率不高。
//     * 如果是1，那意味着每次空间使用完毕才扩容，在一定程度上会增加put时候的时间。
//     *
//     * 最好的loadfactor的值为log（2），约等于0.693，可能小于0.75 大于等于log（2）的factor都能提供更好的性能；
//     * 再说capacity是2的幂，capacity * 0.75能够得到一个整数，例如16*0.75=12。
//     */
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    // 链表节点转换红黑树节点的阈值, 9个节点转
//    static final int TREE_THRESHOLD = 8;
//
//    // 红黑树节点转换链表节点的阈值, 6个节点转
//    static final int UNTREE_THRESHOLD = 6;
//
//    // 转红黑树时, table的最小长度
//    static final int MIN_TREE_CAPACITY = 64;
//
//    /**
//     * 桶
//     * 每个桶里面可以存放若干个KV对
//     * 桶里面的数据称为bin
//     */
//    private Node<K, V>[] table;
//
//    private Set<MyMap.MyEntry<K, V>> entrySet;
//
//    /**
//     * hashMap中存放KV对的数量
//     * 并不是桶的数量，capacity才是桶的数量，默认16
//     */
//    private int size;
//
//    /**
//     * 加载因子
//     * 衡量HashMap满的程度，默认值为{@link #DEFAULT_LOAD_FACTOR}
//     * 计算方式：<b>size/capacity</b>
//     */
//    private final float loadFactor;
//
//    /**
//     * 当HashMap的size到达threshold这个阈值时会扩容
//     * 计算方式：<b>capacity*loadFactor</b>
//     */
//    private final int threshold;
//
//    static class Node<K, V> implements MyMap.MyEntry<K, V> {
//
//        final int hash;
//        final K key;
//        V value;
//        Node<K, V> next;
//
//        Node(int hash, K key, V value, Node<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @Override
//        public final K getKey() {
//            return key;
//        }
//
//        @Override
//        public final V getValue() {
//            return value;
//        }
//
//        @Override
//        public final V setValue(V value) {
//            V oldValue = this.value;
//            this.value = value;
//            return oldValue;
//        }
//
//        @Override
//        public String toString() {
//            return key + "=" + value;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (obj == this) {
//                return true;
//            }
//            if (obj instanceof MyMap.MyEntry) {
//                MyMap.MyEntry<?, ?> e = (MyMap.MyEntry<?, ?>) obj;
//                if (key.equals(e.getKey()) && value.equals(e.getValue())) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//
//    /**
//     * 红黑树节点
//     * @param <K>
//     * @param <V>
//     */
//    static final class TreeNode<K, V> extends MyLinkedHashMap.Entry<K, V> {
//        TreeNode<K, V> parent;
//        TreeNode<K, V> left;
//        TreeNode<K, V> right;
//        TreeNode<K, V> prev; // 当删除一个节点后，需要取消链接
//        boolean red;
//
//        TreeNode(int hash, K key, V value, Node<K, V> next) {
//            super(hash, key, value, next);
//        }
//
//        /**
//         * 返回包含此节点的树的根
//         */
//        final TreeNode<K, V> root() {
//            for (TreeNode<K, V> r = this, p ;;) {
//                // 将当前节点赋值给r，并将r的父节点赋值给p，当p没有父节点时，表示此时p节点就是数的根
//                if ((p = r.parent) == null) {
//                    return r;
//                }
//                r = p; // 将p（r的父节点）赋值给r，从当前节点向树根移动
//            }
//        }
//
//        /**
//         * 确保给定的根是其bin的第一个节点。
//         * 将当前root放到头结点的位置
//         * 如果当前索引位置的头节点不是root节点, 则将root的上一个节点和下一个节点进行关联,
//         * 将root放到头节点的位置, 原头节点放在root的next节点上
//         */
//        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
//            int n;
//            if (root != null && tab != null && (n = tab.length) > 0) {
//                int index = (n - 1) & root.hash; // 计算当前root结点的索引
//                TreeNode<K, V> first = (TreeNode<K, V>) tab[index]; // 获取table中root节点索引位置对应的第一个节点
//                if (root != first) { // 如果root节点不是头结点，则将该位置节点替换为root节点
//                    Node<K, V> rn; // 将保存root的next
//                    tab[index] = root; // 将root 赋值给 table index处的头结点
//                    TreeNode<K, V> rp = root.prev; // root的上一个节点
//                    // 移除root节点，将root节点从树种断开，断开一个节点要断开prev和next两个节点
//                    if ((rn = root.next) != null) { // 将root的next赋值给rn，并判断是不是null
//                        // 如果root节点的next节点不为空，则将root节点的next节点的prev属性设置为root节点的prev节点
//                        ((TreeNode<K, V>) rn).prev = rp;
//                    }
//                    if (rp != null) { // root的prev节点不为空
//                        rp.next = rn; // 将root节点的prev节点的next节点赋值给root节点的prev节点
//                    }
//                    // 将first节点放置到root节点的next
//                    if (first != null) { // 该索引处的头节点不为空
//                        first.prev = root; // 将root节点放置到first节点的前面
//                    }
//                    // 将first节点放置到root节点的后面
//                    root.next = first;
//                    // 此时root节点已是头结点，因此将其prev设置为null
//                    root.prev = null;
//                }
//                //  检查root节点
//            }
//        }
//
//        /**
//         * 从调用此方法的节点开始查找, 通过hash值和key找到对应的节点
//         * 从根节点p开始查找指定hash值和关键字key的结点
//         *
//         * 此方法是红黑树节点的查找, 红黑树是特殊的自平衡二叉查找树
//         * 平衡二叉查找树的特点：左节点<根节点<右节点
//         */
//        final TreeNode<K, V> find(int hash, Object key, Class<?> kc) {
//            // 将p节点赋值为调用此方法的节点，即红黑树的根节点
//            TreeNode<K, V> p = this;
//            do {
//                int ph; // 将存储p节点的hash值
//                int dir; //
//                K pk; // p节点的key值
//                TreeNode<K, V> pl = p.left; // p节点的左子节点
//                TreeNode<K, V> pr = p.right; // p节点的右子节点
//                TreeNode<K, V> q; //
//                // 左节点<根节点<右节点
//                // 如果p节点的hash值大于传入的hash值，那么就向左遍历
//                if ((ph = p.hash) > hash) {
//                    p = pl;
//                }
//                // 如果p节点的hash值小于传入的hash值，那么就向右遍历
//                else if (ph < hash) {
//                    p = pr;
//                }
//                // 如果两p节点的key和传入的key是同一个对象，或者p节点的key和传入的key相等且hash值相同，那么当前p节点就是要找的结点，直接返回p节点即可
//                else if ((pk = p.key) == key || (key != null && key.equals(pk))) {
//                    return p;
//                }
//                else if (pl == null) {
//                    p = pr;
//                }
//                else if (pr == null) {
//                    p = pl;
//                }
//                else if ((kc != null ||
//                        (kc = comparableClassFor(k)) != null) &&
//                        (dir = compareComparables(kc, k, pk)) != 0)
//                    p = (dir < 0) ? pl : pr;
//                else if ((q = pr.find(hash, key, kc)) != null) {
//                    return q;
//                }
//                else {
//                    p = pl;
//                }
//            } while(p != null);
//            return null;
//        }
//
//    }
//
//    // 构造方法
//
//    /**
//     * 构造一个指定初始容量和负载因子的空hashmap
//     * @param initCapacity 容量
//     * @param loadFactor 装载因子
//     */
//    public MyHashMap(int initCapacity, float loadFactor) {
//        if (initCapacity < 0) {
//            throw new IllegalArgumentException("容量" + initCapacity + "不能小于0");
//        }
//        if (initCapacity > MAX_CAPACITY) {
//            initCapacity = MAX_CAPACITY;
//        }
//        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
//            throw new IllegalArgumentException("负载因子 " + loadFactor + " 不合法");
//        }
//        this.loadFactor = loadFactor;
//        // 这里threshold没有使用capacity*loadFactor，因为此时，hashmap是个空的，成员变量还没初始化
//        // table的初始化推迟到put方法中去了，在put方法中会对threshold重新计算
//        this.threshold = tableSizeFor(initCapacity);
//    }
//
//    /**
//     * 构造一个空的hashmap，具有指定的初始容量和默认的负载因子（0.75）
//     * @param initCapacity 初始容量
//     */
//    public MyHashMap(int initCapacity) {
//        this(initCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    /**
//     * 构造一个空的hashmap，具有默认的初始容量（16）和默认的负载因子（0.75）
//     */
//    public MyHashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public boolean containsKey(Object key) {
//        return false;
//    }
//
//    @Override
//    public boolean containsValue(Object value) {
//        return false;
//    }
//
//    @Override
//    public V get(Object key) {
//        return null;
//    }
//
//    @Override
//    public V put(K key, V value) {
//        return null;
//    }
//
//    @Override
//    public V remove(Object key) {
//        return null;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Set<K> keySet() {
//        return null;
//    }
//
//    @Override
//    public Set<MyEntry<K, V>> entrySet() {
//        return null;
//    }
//
//
//    private final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
//        return null;
//    }
//
//    /**
//     * 用于Map.get方法
//     * @param hash key的hash值
//     * @param key 键值
//     * @return 节点，如果为空返回null
//     */
//    private final Node<K, V> getNode(int hash, Object key) {
//        Node<K, V> table;
//        Node<K, V> first, e;
//        int n;
//        K k;
//
//        return null;
//    }
//
//    // static 方法
//
//    /**
//     * 计算key的hash值
//     * 将 hashCode 的高 16 位与 hashCode 进行异或运算，
//     * 主要是为了在 table 的 length 较小的时候，让高位也参与运算，并且不会有太大的开销。
//     * @param key key
//     */
//    private static int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    /**
//     * 找到大于等于initialCapacity的最小的2的幂（initialCapacity如果就是2的幂，则返回的还是这个数）
//     * @param cap 指定的容量
//     * @return 大于等于initialCapacity的最小的2的幂
//     */
//    private static int tableSizeFor(int cap) {
//        /**
//         * 为了防止，cap已经是2的幂。
//         * 如果cap已经是2的幂，又没有执行这个减1操作，则执行完后面的几条无符号右移操作之后，返回的capacity将是这个cap的2倍数
//         */
//        int n = cap - 1;
//
//        // 无符号右移
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
//    }
//
//
//
//}
