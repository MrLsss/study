package com.study.java.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月09日 23:19
 */
public class IOTest {

    public static void main(String[] args) {
        File file = new File("/Users/liushuai/Desktop/test.txt");
        try {
            file.deleteOnExit();
            writer(file);
            Thread.sleep(2000);
            String reader = reader(file);
            System.out.println(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========================================== 字节流 ========================================================
    // 字节流 效率较低，不建议使用
    /**
     * 将数据写入文件中
     */
    public static void byteWrite(File file) throws IOException {
        OutputStream os = new FileOutputStream(file, true);
        final String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        os.write(string.getBytes());
        os.close();
    }

    /**
     * 从文件读取数据
     */
    public static String byteRead(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        // 用来接收读取到的数据
        StringBuilder sb = new StringBuilder();
        // 一次读取多少字节数组
        byte[] bytes = new byte[1024];
        // 读取到的直接数组长度，为-1的时候表示没有数据了
        int length = 0;
        while((length = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, length));
        }

        is.close();
        return sb.toString();
    }

    // ========================================== 缓冲字节流 ========================================================
    // 缓冲字节流是为高效率而设计的，真正的读写操作还是靠FileOutputStream和FileInputStream，所以其构造方法入参是这两个类的对象也就不奇怪了。

    public static void bufferedWrite(File file) throws IOException {
        FileOutputStream fis = new FileOutputStream(file, true);
        // 缓冲字节流，提高了效率
        BufferedOutputStream bos = new BufferedOutputStream(fis);
        final String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        bos.write(string.getBytes());
        bos.close();
        fis.close();
    }

    public static String bufferedRead(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] bytes = new byte[1024];
        int length;
        StringBuilder sb = new StringBuilder();
        while((length = bis.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, length));
        }
        return sb.toString();
    }

    // ========================================== 字符流 ========================================================
    // 字符流适用于存文本文件的读写，OutputStreamWriter类其实也是借助FileOutputStream类实现的，故其构造方法是FileOutputStream的对象

    public static void writer(File file) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
        final String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        osw.write(string);
        osw.close();
    }

    public static String reader(File file) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        // 字符数组：一次读取多少个字符
        char[] chars = new char[1024];
        int length;
        StringBuilder sb = new StringBuilder();
        while((length = isr.read(chars)) != -1) {
            sb.append(chars, 0, length);
        }
        isr.close();
        return sb.toString();
    }

    // ========================================== 字符流便捷类 ========================================================
    // Java提供了FileWriter和FileReader简化字符流的读写，new FileWriter等同于new OutputStreamWriter(new FileOutputStream(file, true))

    public static void fileWriter(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        final String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        fw.write(string);
        fw.close();
    }

    public static String fileReader(File file) throws IOException {
        FileReader fr = new FileReader(file);
        char[] chars = new char[1024];
        StringBuilder sb = new StringBuilder();
        int length;
        while((length = fr.read(chars)) != -1) {
            sb.append(chars, 0, length);
        }
        return sb.toString();
    }

    // ========================================== 字符缓冲流 ========================================================

    public static void bufferedWriter(File file) throws IOException {
        // BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
        // FileWriter可以大幅度简化代码
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        final String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        bw.write(string);
        bw.close();
    }

    public static String bufferedReader(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        int length;
        char[] chars = new char[1024];
        StringBuilder sb = new StringBuilder();

        while((length = br.read(chars)) != -1) {
            sb.append(chars, 0, length);
        }
        br.close();
        return sb.toString();
    }

}
