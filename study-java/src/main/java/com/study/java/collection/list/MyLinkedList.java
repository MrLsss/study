package com.study.java.collection.list;


import com.study.java.collection.interfaces.MyDeque;
import com.study.java.collection.interfaces.MyList;

import java.util.NoSuchElementException;

/**
 * linkedList实现 接口
 * <p>采用双向链表实现，一个指针指向头结点，一个指针指向尾节点
 * 这样可以针对头部和尾部进行操作
 */
public class MyLinkedList<E> implements MyList<E>, MyDeque<E> {

    /**
     * 双端队列中的节点对象
     * @param <E> 元素类型
     */
    private static class Node<E> {
        // 当前节点
        E item;
        // 后一个节点
        Node<E> next;
        // 前一个节点
        Node<E> prev;

        Node(E element) {
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

    private int size = 0;

    // 当first = last的时候，表示此时链表中只有一个节点
    /**
     * 该节点始终指向链表中的第一个节点
     */
    private Node<E> first;

    /**
     * 该节点始终指向链表中的最后一个节点
     */
    private Node<E> last;

    public MyLinkedList() {}

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
        return indexOf(o) >= 0;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        /* 1.先将第一个元素保存 */
        final Node<E> f = first;
        /* 2.判断第一个元素是否为空，为空就抛出异常，否则返回最后元素 */
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    @Override
    public E getLast() {
        /* 1.先将最后一个元素保存 */
        final Node<E> l = last;
        /* 2.判断最后一个元素是否为空，为空就抛出异常，否则返回最后元素 */
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    /**
     * 链表是容量是动态的，所以不会出现容量限制的情况
     * 那么该方法和{@link #add}是一样的
     * @param e 需要入队的元素
     */
    @Override
    public boolean offer(E e) {
        add(e);
        return true;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    @Override
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : removeLinkedFirst();
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : removeLinkedLast();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E remove() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return removeFirst();
    }

    @Override
    public boolean add(E e) {
        addLinkedLast(e);
        return true;
    }

    @Override
    public void addFirst(E e) {
        addLinkedFirst(e);
    }

    @Override
    public void addLast(E e) {
        addLinkedLast(e);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    removeLinked(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.next) {
                if (x.item.equals(o)) {
                    removeLinked(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return removeLinkedFirst();
    }

    @Override
    public E removeLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return removeLinkedLast();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界: " + index);
        }
        return removeLinked(node(index));
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界: " + index);
        }
        return node(index).item;
    }

    @Override
    public E set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界: " + index);
        }
        Node<E> node = node(index);
        E oldVal = node.item;
        node.item = e;
        return oldVal;
    }

    @Override
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界: " + index);
        }
        if (index == size) {
            addLinkedLast(e);
        } else {
            addLinkedBefore(e, node(index));
        }
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index ++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item.equals(o)) {
                    return index;
                }
                index ++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index --;
                if (x.item == null) {
                    return index;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index --;
                if (x.item.equals(o)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList: [");
        for (Node<E> x = first; x != null; x = x.next) {
            sb.append("{").append(x.item.toString()).append("}").append(",");
        }
        String substring = sb.substring(0, sb.lastIndexOf(","));
        substring += "]";
        return substring;
    }

    /**
     * 返回索引处的结点
     * @param index 索引
     * @return 索引处的结点
     */
    private Node<E> node(int index) {
        // 如果索引小于 size/2 ，就从链表的头开始查，否则就从尾开始查

        if (index < (size / 2)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 删除链表中指定的元素，并返回该元素
     * @param e 需要删除的元素
     * @return 删除的元素
     */
    private E removeLinked(Node<E> e) {
        /* 1.先保存指定节点e，e的前置结点，e的后置结点 */
        final E element = e.item;
        final Node<E> prev = e.prev;
        final Node<E> next = e.next;

        /* 2.如果结点e是头结点，则e的前置结点是null，此时就要将first指针指向e结点的下一个结点 */
        if (prev == null) {
            first = next;
        } else {
            /* 3.如果结点e不是头结点，那么将e的前置结点的next指向e的next，并将e的前置结点从链表中断开 */
            prev.next = next;
            e.prev = null;
        }

        /* 4.如果结点e是尾结点，则e的后置结点是null，此时就要将last结点指针指向e结点的前一个结点 */
        if (next == null) {
            last = prev;
        } else {
            /* 5.如果结点e不是尾结点，那么将e的后置结点的prev指向e的prev，并将e的后置结点从链表中断开 */
            next.prev = prev;
            e.next = null;
        }

        /* 6.将结点e设置为null */
        e.item = null;
        size --;
        return element;
    }

    /**
     * 删除链表的第一个元素，并返回删除前的第一个元素
     * @return 返回删除前的第一个元素
     */
    private E removeLinkedFirst() {
        /* 1.先保存第一个元素和第二个节点 */
        final E element = first.item;
        final Node<E> next = first.next;
        /* 2.将头指针指向第二个元素 */
        first = next;
        /* 3.如果第二个元素是null，那么first此时是null，则将last也设置为null，该情况说明队列中只有一个元素 */
        if (next == null) {
            last = null;
        } else { // 队列中有多个元素时，那么此时将第二个元素设置为头部
            next.prev = null;
        }
        size --;
        return element;
    }

    /**
     * 删除链表的最后一个元素，并返回删除前的最后一个元素
     * @return 删除前的最后一个元素
     */
    private E removeLinkedLast() {
        final E element = last.item;
        final Node<E> prev = last.prev;
        last = prev;
        /* 若果队列中只有一个元素，那么删除之后队列为空，此时prev=last=null，也将first设置为null */
        if (prev == null) {
            first = null;
        } else {
            /* 如果队列中有多个元素，那么将倒数第二个元素设置为最后一个元素 */
            prev.next = null;
        }
        size --;
        return element;
    }

    /**
     * 在指定节点node之前插入新元素e
     * @param e 需要插入的新元素
     * @param node 指定结点
     */
    private void addLinkedBefore(E e, Node<E> node) {
        /* 1.先保存指定结点node的前置结点 */
        final Node<E> prev = node.prev;
        /* 2.构造新节点newNode, newNode的后置结点是指定结点node，newNode的前置结点是指定结点node的前置结点 */
        Node<E> newNode = new Node<>(prev, e, node);
        /* 3.指定结点的前置结点设置为新节点newNode */
        node.prev = newNode;
        /* 4.如果指定结点是头结点，那么此时newNode将变成头结点，则first指向newNode */
        if (prev == null) {
            first = newNode;
        } else {
            /* 5.如果指定结点不是头结点，指定结点node的前置结点的后一个结点设置为newNode */
            prev.next = newNode;
        }
        size ++;
    }

    /**
     * 在链表的最后添加节点e
     * @param e 新添加的节点
     */
    private void addLinkedLast(E e) {
        /* 1. 先将最后一个节点保存，方便后续的操作 */
        final Node<E> l = last;
        /* 2. 将新添加的元素转换成节点 */
        final Node<E> addNode = new Node<>(l, e, null);
        /* 3. last节点指向新的节点，也就是last指向最后一个节点 */
        last = addNode;
        /* 4. 添加节点 */
        if (l == null) { // 意思是当last=null的时候，链表是空的
            first = addNode; // 那么就将该节点设置为链表的第一个节点
        } else {
            l.next = addNode; // 否则，就将新节点加在最后
        }
        size ++;
    }

    /**
     * 在链表的头部新增节点
     * @param e 需要新增的元素
     */
    private void addLinkedFirst(E e) {
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
