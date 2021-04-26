# 成员变量、常量及内部类
- DEFAULT_INIT_CAPACITY：默认的初始容量=1<<4=16
- MAX_CAPACITY：最大容量=1<<30，如果指定了更大的容量，则默认使用该容量
- DEFAULT_LOAD_FACTOR：默认负载因子=0.75
  - 默认的加载因子是0.75，默认初始容量是16，因此可以得出HashMap的默认容量是：0.75*16=12
  - 如果是0.5的话，每次达到容量的一半就要扩容，默认容量是16，达到8就扩容成32，达到16就扩容，到后来使用的空间和未使用的差额会越来越大，空间利用率不高。
  - 如果是1，那意味着每次空间使用完毕才扩容，在一定程度上会增加put时候的时间。
- TREE_THRESHOLD：链表节点转换红黑树节点的阈值，9个节点转
- UNTREE_THRESHOLD：红黑树节点转换链表节点的阈值, 6个节点转
- MIN_TREE_CAPACITY：转红黑树时, table的最小长度
- table：桶，每个桶里面可以存放若干个KV对，桶里面的数据称为bin
- loadFactor：加载因子，用来衡量HashMap满的程度
- threshold：临界值（相当于capacity），当HashMap的size到达threshold这个阈值时会扩容
- Node<K, V>：链表节点
- TreeNode<K, V>：红黑树节点

# 构造方法

```java
/**
 * 空参构造方法
 * 默认初始容量：16
 * 默认负载因子：0.75
 */
public HashMap() {
  this.loadFactor = DEFAULT_LOAD_FACTOR;
}

/**
 * 指定容量的构造方法
 * 默认负载因子：0.75
 */
public HashMap(int initialCapacity) {
  this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

/**
 * 指定容量和负载因子
 */
public HashMap(int initialCapacity, float loadFactor) {
  if (initialCapacity < 0) // 容量判断
    throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
  if (initialCapacity > MAXIMUM_CAPACITY) // 容量判断
    initialCapacity = MAXIMUM_CAPACITY;
  if (loadFactor <= 0 || Float.isNaN(loadFactor)) // 负载因子判断
    throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
  this.loadFactor = loadFactor;
  this.threshold = tableSizeFor(initialCapacity); 
  // tableSizeFor：找到大于等于initialCapacity的最小的2的幂（initialCapacity如果就是2的幂，则返回的还是这个数）
}
```

# Node

当是链表的时候，用Node存储

```java
static class Node<K,V> implements Map.Entry<K,V> {
  final int hash;
  final K key;
  V value;
  Node<K,V> next;

  Node(int hash, K key, V value, Node<K,V> next) {
    this.hash = hash;
    this.key = key;
    this.value = value;
    this.next = next;
  }

  public final K getKey()        { return key; }
  public final V getValue()      { return value; }
  public final String toString() { return key + "=" + value; }

  public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
  }

  public final V setValue(V newValue) {
    V oldValue = value;
    value = newValue;
    return oldValue;
  }

  public final boolean equals(Object o) {
    if (o == this)
      return true;
    if (o instanceof Map.Entry) {
      Map.Entry<?,?> e = (Map.Entry<?,?>)o;
      if (Objects.equals(key, e.getKey()) &&
          Objects.equals(value, e.getValue()))
        return true;
    }
    return false;
  }
}
```

# TreeNode

当是红黑树的时候，用TreeNode存储

## 定义

```java
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
  TreeNode<K,V> parent;
  TreeNode<K,V> left;
  TreeNode<K,V> right;
  TreeNode<K,V> prev;
  boolean red;
  TreeNode(int hash, K key, V val, Node<K,V> next) {
    super(hash, key, val, next);
  }
}
```
## root

```java
/**
 * 找到当前节点的根节点
 */
final TreeNode<K,V> root() {
  for (TreeNode<K,V> r = this, p;;) {
    if ((p = r.parent) == null)
      return r;
    r = p;
  }
}
```

## moveRootToFront


```java
/**
 * 通过该方法来维护root节点（红黑树的根节点）就是table索引位置的头节点
 * 总结：
 * 如果当前索引位置的头节点不是root节点, 则将root的上一个节点和下一个节点进行关联
 * 将root放到头节点的位置, 原头节点放在root的next节点上
 */
static <K,V> void moveRootToFront(Node<K,V>[] tab, TreeNode<K,V> root) {
  int n;
  if (root != null && tab != null && (n = tab.length) > 0) {
    // 计算当前root结点的索引
    int index = (n - 1) & root.hash;
    // 获取table中root节点索引位置的头结点，设置为first
    TreeNode<K,V> first = (TreeNode<K,V>)tab[index];
    // 如果root节点不是头结点，则需要将该位置节点替换为root节点
    if (root != first) {
      Node<K,V> rn; // 将存储root节点的next节点
      tab[index] = root; // 将root节点赋值给table的index索引处的头结点
      TreeNode<K,V> rp = root.prev; // root的上一个节点
      // 移除root节点，将root节点从树中断开
      if ((rn = root.next) != null)
        ((TreeNode<K,V>)rn).prev = rp; // 如果root节点的next节点不为空，则将root节点的next节点的prev节点设置为root节点的prev节点
      if (rp != null)
        rp.next = rn; // 如果root节点的prev节点不为空，则将root节点的prev节点的next节点设置为root节点的next节点
      // 将first节点放置到root节点的next
      if (first != null) // 该索引处的头节点不为空
        first.prev = root; // 将root节点放置到first节点的前面
      // 将first节点放置到root节点的next
      root.next = first;
      // 此时root节点已是头结点，因此将其prev设置为null
      root.prev = null;
    }
    // 校验节点的合法性，主要是保证该树符合红黑树的规则
    assert checkInvariants(root); 
  }
}
```

## find

```java
/**
  * 通过hash值和key找到对应的节点
  * 从根节点p开始查找指定hash值和关键字key的结点
  
  * 此方法是红黑树节点的查找, 红黑树是特殊的自平衡二叉查找树
  * 平衡二叉查找树的特点：左节点<根节点<右节点
  */
final TreeNode<K, V> find(int hash, Object key, Class<?> kc) {
  // 将p节点赋值为调用此方法的节点，即红黑树的根节点
  TreeNode<K, V> p = this;
  do {
    int ph; // 将存储p节点的hash值
    int dir; //
    K pk; // p节点的key值
    TreeNode<K, V> pl = p.left; // p节点的左子节点
    TreeNode<K, V> pr = p.right; // p节点的右子节点
    TreeNode<K, V> q; //
    // 左节点<根节点<右节点
    // 如果p节点的hash值大于传入的hash值，那么就向左遍历
    if ((ph = p.hash) > hash) {
      p = pl;
    }
    // 如果p节点的hash值小于传入的hash值，那么就向右遍历
    else if (ph < hash) {
      p = pr;
    }
    // 如果两p节点的key和传入的key是同一个对象，或者p节点的key和传入的key相等且hash值相同，那么当前p节点就是要找的结点，直接返回p节点即可
    else if ((pk = p.key) == key || (key != null && key.equals(pk))) {
      return p;
    }
    // 如果左子节点为null，那么就向右遍历
    else if (pl == null) {
      p = pr;
    }
    // 如果右子节点为null，那么就向左遍历
    else if (pr == null) {
      p = pl;
    }
    // 将p节点和key进行比较
    else if ((kc != null ||
              (kc = comparableClassFor(key)) != null) && // kc不为空代表k实现了Comparable
             (dir = compareComparables(kc, key, pk)) != 0) // key<pk则dir<0, key>pk则dir>0
      p = (dir < 0) ? pl : pr; // key<pk则向左遍历(p赋值为p的左节点), 否则向右遍历
    // 代码走到此处, 代表key所属类没有实现Comparable, 直接指定向p的右边遍历
    else if ((q = pr.find(hash, key, kc)) != null) {
      return q;
    }
    // 代码走到此处代表“pr.find(h, k, kc)”为空, 因此直接向左遍历
    else {
      p = pl;
    }
  } while (p != null);
  return null;
}
```

## comparableClassFor

```java
static Class<?> comparableClassFor(Object x) {
  if (x instanceof Comparable) {
    Class<?> c;
    Type[] ts, as;
    Type t;
    ParameterizedType p;
    if ((c = x.getClass()) == String.class) {
      return c;
    }
    if ((ts = c.getGenericInterfaces()) != null) {
      for (int i = 0; i < ts.length; ++i) {
        if (((t = ts[i]) instanceof ParameterizedType) &&
            ((p = (ParameterizedType) t).getRawType() == Comparable.class &&
             (as = p.getActualTypeArguments()) != null &&
             as.length == 1 && as[0] == c)) {
          return c;
        }
      }
    }
  }
  return null;
}
```

## compareComparables

```java
static int compareComparables(Class<?> kc, Object k, Object x) {
  return (x == null || x.getClass() != kc ? 0 : ((Comparable)k).compareTo(x));
}
```











> 参考链接：https://blog.csdn.net/v123411739/article/details/78996181