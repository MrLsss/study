package com.study.list;


import java.io.Serializable;
import java.util.Arrays;

/**
 * arrayList实现
 *
 * <p>
 * 在所有添加数据的操作上面都要需要判断当前数组容量是否足以容纳新的数据，如果不够的话就需要进行扩容。
 * </p>
 */
public class MyArrayList<E> implements MyList<E>, Serializable {

    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 默认的初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 出现在需要用到空数组的地方，其中一处就是使用自定义初始容量构造方法时候如果你指定初始容量为0的时候就会返回该数组。
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * 跟前面的区别就是这个空数组是用来判断ArrayList第一添加数据的时候要扩容多少。默认的构造器情况下返回这个空数组。
     * 如果是第一次添加数据的话，那么数组扩容长度为 DEFAULT_CAPACITY
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    /**
     * ArrayList中元素的个数，并不是容量
     */
    private int size;

    /**
     * 存储ArrayList的元素的数组缓冲区。
     * ArrayList的容量是此数组缓冲区的长度。
     * 添加第一个元素时，任何具有elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA的空ArrayList都将扩展为DEFAULT_CAPACITY
     */
    private Object[] elementData;

    /**
     * 要分配的最大数组大小
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 无参构造方法。
     * 如果在初始化ArrayList时，没有指定初始容量，就会返回一个长度为0的空数组。
     */
    public MyArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("非法的初始容量：" + capacity);
        }
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return elementData.length;
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
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除元素
     * @param index 元素索引
     */
    private void fastRemove(int index) {
        // index后面的元素数量
        int moveNum = size - index - 1;
        if (moveNum > 0) {
            // 从index+1后面的元素依次向前移动
            System.arraycopy(elementData, index + 1, elementData, index, moveNum);
        }
        elementData[size] = null;
        size --;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("传入的索引越界！");
        }
        E oldValue = elementData(index);
        int moveNum = size - index - 1;
        if (moveNum > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, moveNum);
        }
        elementData[size] = null;
        size --;
        return oldValue;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("传入的索引越界！");
        }
        return elementData(index);
    }

    @Override
    public E set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("传入的索引越界！");
        }
        E oldValue = elementData(index);
        elementData[index] = e;
        return oldValue;
    }

    @Override
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("传入的索引越界！");
        }
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size ++;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 将此ArrayList实例的容量调整为列表的当前大小
     */
    public void trimToSize() {
        if (size < elementData.length) {
            elementData = (size == 0) ? EMPTY_ELEMENT_DATA : Arrays.copyOf(elementData, size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i].toString());
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 计算容量
     * 判断当前的数组是否是通过默认的构造方法生成的空数组，如果是的话 minCapacity=10，反之则根据原来的值传入下一个方法去完成下一步的扩容判断
     *
     * @param elementData 数组
     * @param minCapacity 输入的容量
     * @return 取两者的最大值
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        // 判断是否是通过默认无参构造方法创建的
        if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            // 如果是无参构造方法创建的，那么容量就是10
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * 扩容方法入口
     *
     * @param minCapacity 表示修改后的容量，例如：minCapacity = size + 1
     */
    private void ensureCapacityInternal(int minCapacity) {
        // 判断是否需要扩容
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     * 判断当前ArrayList是否需要进行扩容
     *
     * @param minCapacity 通过计算后的需要扩容的容量
     */
    private void ensureExplicitCapacity(int minCapacity) {
        // 快速报错机制
        // modCount++

        // 如果修改后的数组容量大于当前的数组长度那么就需要调用grow进行扩容
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 扩容实现
     *
     * @param minCapacity 需要扩容的容量
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // 1.5倍扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 如果新容量小于所需最小容量，那么就扩容到所需最小容量
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        // 如果扩大的容量超过了最大容量MAX_ARRAY_SIZE，就要调用hugeCapacity()方法，根据所需最小所需容量来确定扩容后的容量
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 当扩容的容量超过最大容量，根据minCapacity判断后续需要扩容的容量
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }


}
