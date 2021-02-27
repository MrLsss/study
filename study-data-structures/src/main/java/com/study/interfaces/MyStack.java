package com.study.interfaces;

/**
 * 栈接口
 * 推荐使用{@link MyDeque}
 */
public interface MyStack<E> {

    /**
     * 查看栈顶元素，不删除该元素
     * @return 最后一次入栈的元素
     */
    E peek();

    /**
     * 从堆栈的顶部向下（相对零）返回第n个项目，而不将其删除
     * @param n 从栈顶向下的第n个元素
     * @return 返回指定位置的元素
     */
    E peek(int n);

    /**
     * 出栈
     * @return 返回栈顶元素
     */
    E pop();

    /**
     * 入栈
     * @param item 入栈的元素
     * @return 入栈元素
     */
    E push(E item);

}
