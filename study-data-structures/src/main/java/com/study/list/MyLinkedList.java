package com.study.list;


/**
 * linkedList实现
 * <p>采用双向链表实现，一个指针指向头结点，一个指针指向尾节点
 * 这样可以针对头部和尾部进行操作
 */
public class MyLinkedList<E> implements MyList<E> {

    public MyLinkedList() {}

    private int size = 0;

    private class Node<E> {
        // 当前节点
        E item;
        // 后一个节点
        Node<E> next;
        // 前一个节点
        Node<E> prev;

        public Node(E element) {
            this.item = element;
            this.next = null;
            this.prev = null;
        }

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    // 当first = last的时候，表示此时链表中只有一个节点
    /**
     * 该节点始终指向链表中的第一个节点
     */
    private Node<E> first;

    /**
     * 该节点始终指向链表中的最后一个节点
     */
    private Node<E> last;

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(E e, int index) {
        return null;
    }

    @Override
    public void add(E e, int index) {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    /**
     * 在链表的最后添加节点e
     * @param e 新添加的节点
     */
    private void addLast(E e) {
        /* 1. 先将最后一个节点保存，方便后续的操作 */
        final Node<E> l = last;
        /* 2. 将新添加的元素转换成节点 */
        Node<E> addNode = new Node<>(l, e, null);
        /* 3. last节点指向新的节点，也就是last指向最后一个节点 */
        last = addNode;
        /* 4. 添加节点 */
        if (l == first) { // 意思是当last=first的时候，链表是空的
            first = addNode; // 那么就将该节点设置为链表的第一个节点
        } else {
            l.next = addNode; // 否则，就将新节点加在最后
        }
        size ++;
    }

    private void addFirst(E e) {
        final Node<E> f = first;
        Node<E> addNode = new Node<>(null, e, f);
        first = addNode;
        if (f == null) {
            last = addNode;
        } else {
            f.prev = addNode;
        }
        size ++;
    }
}
