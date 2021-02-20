package com.study.list;

/**
 * 测试
 */
public class Test {

    public static void main(String[] args) {
        myArrayListTest();
    }

    public static void myArrayListTest() {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i < 25; i++) {
            list.add(String.valueOf(i));
        }
//        list.add("6", 2);
//        System.out.println(list.size());
        System.out.println(list.toString());
//        list.remove("2");
//        System.out.println(list.size());
//        System.out.println(list.remove(list.size() - 1));
//        System.out.println(list.size());
//        System.out.println(list.toString());
//        System.out.println(list.capacity());
        System.out.println(list.get(list.size()));
    }
}
