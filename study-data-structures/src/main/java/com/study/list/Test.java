package com.study.list;

/**
 * 测试
 */
public class Test {

    public static void main(String[] args) {
//        myArrayListTest();
        myLinkedList();
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

    public static void myLinkedList() {
        MyLinkedList<String> list = new MyLinkedList<>();
        for (int i = 0; i < 25; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("add: " + list.toString());
        list.addFirst("000");
        list.addLast("999");
        System.out.println("addFirst & addLast: " + list.toString());
        System.out.println("pop: " + list.pop());
        System.out.println("pop after: " + list.toString());
        System.out.println("set: " + list.set("888", 15));
        System.out.println("set after: " + list.toString());
        System.out.println("remove: " + list.remove(22));
        System.out.println("remove after: " + list.toString());
        System.out.println("removeLast: " + list.removeLast());
        System.out.println("removeLast after: " + list.toString());
        System.out.println("isEmpty: " + list.isEmpty());
        System.out.println("size & capacity: " + list.size() + "," + list.capacity());
        System.out.println("poll: " + list.poll());
        System.out.println("poll after: " + list.toString());
        list.push("0");
        System.out.println("push after: " + list.toString());
    }
}
