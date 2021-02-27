package com.study.interfaces;

/**
 * list接口
 */
public interface MyList<E> {

    /**
     * 返回list中元素的个数，如果超过 Integer.MAX_VALUE 数量，则返回 Integer.MAX_VALUE
     * @return list中元素的个数
     */
    int size();

    /**
     * list的容量
     * @return list的容量
     */
    int capacity();

    /**
     * 如果list不包含任何元素则返回true，否则返回false
     * @return true：list不包含任何元素
     */
    boolean isEmpty();

    /**
     * 如果list中包含指定元素，则返回true
     * @param o 要检查其是否存在于此列表中的元素
     * @return true：如果list包含指定的元素
     */
    boolean contains(Object o);

    /**
     * 将指定的元素追加到此列表的末尾。
     * @param e 指定元素
     * @return true：添加成功
     */
    boolean add(E e);

    /**
     * 如果list中包含指定的元素，则从列表中删除该元素的第一个匹配项（可选）
     * @param o 指定的元素
     * @return true：如果list中包含指定的元素
     */
    boolean remove(Object o);

    /**
     * 删除指定索引的元素
     * @param index 要删除的元素的索引
     * @return 先前位于指定位置的元素
     */
    E remove(int index);

    /**
     * 返回此列表中指定位置的元素。
     * @param index 指定位置
     * @return 指定位置的元素
     */
    E get(int index);

    /**
     * 用指定的元素替换此列表中指定位置的元素
     * 如果超出范围则越界
     * @param e 指定元素
     * @param index 指定位置
     * @return index位置原先的元素
     */
    E set(E e, int index);

    /**
     * 将指定的元素插入此列表中的指定位置（可选操作）。 将当前在该位置的元素（如果有）和任何后续元素右移（将其索引加一）。
     * @param e 指定元素
     * @param index 指定位置
     */
    void add(E e, int index);

    /**
     * 返回指定元素在此列表中首次出现的索引；如果此列表不包含该元素，则返回-1
     * @param o 指定元素
     * @return 指定元素的第一次出现的索引
     */
    int indexOf(Object o);

    /**
     * 返回指定元素在此列表中最后一次出现的索引；如果此列表不包含该元素，则返回-1
     * @param o 指定元素
     * @return 指定元素的最后一次出现的索引
     */
    int lastIndexOf(Object o);
}
