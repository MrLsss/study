package com.study.interfaces;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description: 双端队列 Double ended queue，支持两端插入和移除元素
 * 每种方法都存在两种形式：一种形式在操作失败时抛出异常，另一种形式返回一个特殊值（null 或 false，具体取决于操作）
 * 推荐此接口代替{@link MyStack}接口
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月27日 02:06
 */
public interface MyDeque<E> extends MyQueue<E> {

    /**
     * 双端队列中元素的个数
     * @return 双端队列中元素的个数
     */
    int size();

    /**
     * 如果可以在不违反容量限制的情况下立即执行操作，则将指定的元素插入此双端队列表示的队列中（换句话说，在此双端队列的末尾），
     * 如果当前没有可用空间，则返回true并抛出{@code IllegalStateException}
     * 使用容量受限的双端队列时，通常最好使用{@link #offer}
     * 此方法等效于{@link #addLast}
     * @param e 需要插入的元素
     */
    boolean add(E e);

    /**
     * 如果可以在不违反容量限制的情况下立即执行此操作，则将指定的元素插入此双端队列的前面，
     * 如果当前没有可用空间，则抛出IllegalStateException 。
     * 当使用容量受限的双端队列时，通常最好使用方法{@link #offerFirst} 。
     * @param e 需要插入的元素
     */
    void addFirst(E e);

    /**
     * 如果可以立即执行此操作，而不会违反容量限制，则在此双端队列的末尾插入指定的元素；
     * 如果当前没有可用空间，则抛出{@code IllegalStateException}。
     * 使用容量受限的双端队列时，通常最好使用方法{@link #offerLast} 。
     * 此方法等效于{@link #add} 。
     * @param e 需要入队的元素
     */
    void addLast(E e);

    /**
     * 如果此双端队列包含指定的元素，则返回true
     * @param o 指定元素
     * @return true：包含，false：不包含
     */
    boolean contains(Object o);

    /**
     * 检索但不删除此双端队列代表的队列的头（换句话说，此双端队列的第一个元素）。
     * 此方法与{@link #peek}不同之处仅在于，如果此双端队列为空，则它将引发异常{@code NoSuchElementException}
     * 此方法等效于{@link #getFirst}。
     * @return 双端队列的第一个元素
     */
    E element();

    /**
     * 检索但不删除此双端队列的第一个元素。
     * 此方法与{@link #peekFirst}不同之处仅在于，如果此双端队列为空，则它将引发异常{@code NoSuchElementException}
     * @return 双端队列的第一个元素
     */
    E getFirst();

    /**
     * 检索但不删除此双端队列的最后一个元素。
     * 此方法与{@link #peekLast}不同之处仅在于，如果此双端队列为空，则它将引发异常{@code NoSuchElementException}
     * @return 双端队列的最后一个元素
     */
    E getLast();

    /**
     * 如果可以在不违反容量限制的情况下立即执行操作，则将指定的元素插入此双端队列表示的队列中（换句话说，在此双端队列的末尾），
     * 当使用容量受限的双端队列时，此方法通常优于{@link #add}方法，后者可能仅通过引发异常而无法插入元素。
     * 此方法等效于{@link #offerLast}
     * @param e 需要入队的元素
     * @return 如果成功，则返回true ，如果当前没有可用空间，则返回false 。
     */
    boolean offer(E e);

    /**
     * 将指定的元素插入此双端队列的前面，除非会违反容量限制。
     * 当使用容量受限的双端队列时，此方法通常比{@link #addFirst}方法更可取，后者只能通过引发异常而无法插入元素。
     * @param e 需要入队的元素
     * @return 如果将元素添加到此双端队列，则为true ，否则为false
     */
    boolean offerFirst(E e);

    /**
     * 除非此指定的元素违反容量限制，否则将其插入此双端队列的末尾。
     * 当使用容量受限的双端队列时，此方法通常比{@link #addLast}方法更可取，因为addLast方法可能仅通过引发异常而无法插入元素
     * @param e 需要入队的元素
     * @return 如果将元素添加到此双端队列，则为true ，否则为false
     */
    boolean offerLast(E e);

    /**
     * 检索但不删除此双端队列代表的队列的头部（换句话说，此双端队列的第一个元素）
     * 此方法等效于{@link #peekFirst}
     * @return 双端队列代表的队列的头部，如果此双端队列为空，则返回null
     */
    E peek();

    /**
     * 检索但不删除此双端队列的第一个元素，如果此双端队列为空，则返回null
     * @return 双端队列的第一个元素，如果队列为空，返回null
     */
    E peekFirst();

    /**
     * 检索但不删除此双端队列的最后一个元素，如果此双端队列为空，则返回null 。
     * @return 此双端队列的尾部；如果此双端队列为空，则为null
     */
    E peekLast();

    /**
     * 检索并删除此双端队列表示的队列的头部（换句话说，此双端队列的第一个元素），如果此双端队列为空，则返回null 。
     * 此方法等效于{@link #pollFirst}
     * @return 此双端队列的第一个元素；如果此双端队列为空，则为null
     */
    E poll();

    /**
     * 检索并删除此双端队列的第一个元素，如果此双端队列为空，则返回null 。
     * @return 此双端队列的头部；如果此双端队列为空，则为null
     */
    E pollFirst();

    /**
     * 检索并删除此双端队列的最后一个元素，如果此双端队列为空，则返回null
     * @return 此双端队列的尾部；如果此双端队列为空，则为null
     */
    E pollLast();

    /**
     * 从此双端队列表示的堆栈中弹出一个元素。 换句话说，删除并返回此双端队列的第一个元素。
     * 此方法等效于{@link #removeFirst}
     * @return 此双端队列（位于此双端队列表示的堆栈的顶部）前面的元素
     */
    E pop();

    /**
     * 如果可以在不违反容量限制的情况下立即将元素压入此双端队列表示的堆栈（换句话说，此双端队列的头部），
     * 则在当前没有可用空间的情况下{@code IllegalStateException}
     * 此方法等效于{@link #addFirst}
     * @param e 需要入队的元素
     */
    void push(E e);

    /**
     * 检索并删除此双端队列代表的队列的头部（换句话说，此双端队列的第一个元素）
     * 此方法与{@link #poll}不同之处仅在于，如果此双端队列为空，则它将引发异常{@code NoSuchElementException}
     * 此方法等效于{@link #removeFirst}
     * @return 此双端队列代表的队列的头部
     */
    E remove();

    /**
     * 从队列删除第一次出现的指定的元素
     * 如果双端队列不包含元素，则它保持不变。
     * 如果此双端队列包含指定的元素（或者等效地，如果此双端队列由于调用而发生更改），则返回true 。
     * @param o 需要删除的对象
     * @return 如果由于此调用而删除了元素，则为true
     */
    boolean remove(Object o);

    /**
     * 检索并删除此双端队列的第一个元素。
     * 此方法与{@link #pollFirst}不同之处仅在于，如果此双端队列为空，则它将引发异常{@code NoSuchElementException}
     * @return 双端队列的删除前的第一个元素
     */
    E removeFirst();

    /**
     * 检索并删除此双端队列的最后一个元素。
     * 此方法与{@link #pollLast}不同之处仅在于，如果此双端队列为空，则它将引发异常{@code NoSuchElementException}
     * @return 双端队列删除前的最后一个元素
     */
    E removeLast();
}
