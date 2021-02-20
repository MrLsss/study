package com.study.list;

import java.util.EmptyStackException;

/**
 * ArrayStack实现
 */
public class MyArrayStack<E> extends MyArrayList<E> {
    private static final long serialVersionUID = 2130079159931574599L;

    public MyArrayStack() {
    }

    public MyArrayStack(int initialSize) {
        super(initialSize);
    }

    public boolean empty() {
        return this.isEmpty();
    }

    /**
     * 查看栈顶元素
     * @return 最后一次入栈的元素
     */
    public E peek() {
        int n = this.size();
        if (n <= 0) {
            throw new EmptyStackException();
        }
        return this.get(n - 1);
    }

    /**
     * 从堆栈的顶部向下（相对零）返回第n个项目，而不将其删除
     * @param n 从栈顶向下的第n个元素
     * @return 返回指定位置的元素
     */
    public E peek(int n) {
        int m = this.size() - n - 1;
        if (m < 0) {
            throw new EmptyStackException();
        } else {
            return this.get(m);
        }
    }

    /**
     * 出栈
     * @return 返回栈顶元素
     */
    public E pop() {
        int n = this.size();
        if (n <= 0) {
            throw new EmptyStackException();
        } else {
            return this.remove(n - 1);
        }
    }

    /**
     * 入栈
     * @param item 入栈的元素
     * @return 入栈元素
     */
    public E push(E item) {
        this.add(item);
        return item;
    }



}
