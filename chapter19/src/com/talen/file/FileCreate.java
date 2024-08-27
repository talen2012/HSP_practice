package com.talen.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 创建内存中文件对象的三种方式
 * 及在磁盘中创建新文件
 */
public class FileCreate {
    public static void main(String[] args) {

    }

    // 方式1：new File(String pathName)
    @Test
    public void test01() {
        // 路径分割符用两个反斜杠或者一个斜杠都可以
        File file = new File("e:\\Java_practice\\HSP_practice\\chapter19\\news1.txt");
        try {
            file.createNewFile(); // 已存在返回false, 创建成功返回true
            System.out.println("news1.txt创建成功");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    
    // 方式2：new File(File parentFile, String child)
    @Test
    public void test02() {
        File parentFile = new File("e:/Java_practice/HSP_practice/chapter19/");
        File file = new File(parentFile, "news2.txt");
        try {
            file.createNewFile();
            System.out.println("news2.txt创建成功");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    // 方式3：new File(String parent, String child)
    @Test
    public void test03() {
        File file = new File("e:/Java_practice/HSP_practice/chapter19/", "news3.txt");
        try {
            file.createNewFile();
            System.out.println("news3.txt创建成功");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
