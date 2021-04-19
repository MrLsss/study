package com.study.java.collection.interfaces;

import java.util.Set;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description: map接口
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月17日 10:46
 */
public interface MyMap<K, V> {

    /**
     * 返回map中键值对的个数
     */
    int size();

    /**
     * 如果map中没有键值对，返回true
     * @return 如果map中没有键值对，返回true
     */
    boolean isEmpty();

    /**
     * map中是否含有指定key的键值对
     * @param key 指定key
     */
    boolean containsKey(Object key);

    /**
     * 如果map中至少含有一个指定的value，返回true
     * @param value 指定value
     */
    boolean containsValue(Object value);

    /**
     * 返回指定key对应值
     * @param key 指定key
     */
    V get(Object key);

    /**
     * 将指定的键值对放置到map中
     * 如果map中已经含有该key，则用新的value替换旧的value
     * @param key 键
     * @param value 值
     */
    V put(K key, V value);

    /**
     * 从map中移除指定key对应的键值对
     * @param key 指定key
     * @return 移除之前key对应的value
     */
    V remove(Object key);

    void clear();

    Set<K> keySet();

    Set<MyEntry<K, V>> entrySet();

    interface MyEntry<K, V> {

        K getKey();

        V getValue();

        V setValue(V value);

        boolean equals(Object o);

        int hashCode();

    }
}
