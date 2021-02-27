package com.study.interfaces;

/**
 * Queue 队列接口
 */
public interface MyQueue<E> {

    /**
     * 如果可以立即将指定的元素插入此队列，而不会违反容量限制，则在成功时返回true ，如果当前没有可用空间，则IllegalStateException 。
     * @param e 需要插入的元素
     * @return true
     */
    boolean add(E e);

    /**
     * 如果可以立即将指定的元素插入此队列，而不会违反容量限制。
     * 使用容量受限的队列时，通常最好使用add ，因为add可能仅通过引发异常而无法插入元素。
     * @param e 需要入队的元素
     * @return true
     */
    boolean offer(E e);

    /**
     * 检索并删除此队列的头。 此方法与poll不同之处仅在于，如果此队列为空，它将引发异常。
     * @return 队列头部元素
     */
    E remove();

    /**
     * 索并删除此队列的头部，如果此队列为空，则返回null。
     * @return 队列头部元素
     */
    E poll();

    /**
     * 检索但不删除此队列的头。 此方法与peek不同之处仅在于，如果此队列为空，它将引发异常
     * @return 队列头部元素
     */
    E element();

    /**
     * 检索但不删除此队列的头部，如果此队列为空，则返回null 。
     * @return 队列头部元素
     */
    E peek();
}
