package com.talen.inputstream_;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 */
public class FileInputStream_ {
    public static void main(String[] args) {

    }

    @Test
    public void readFile01() {
        FileInputStream fileInputStream = null;
        int readData = 0;
        try {
            // new FileInputStream抛出FileNotFountException
            fileInputStream = new FileInputStream("e:/Java_practice/HSP_practice/chapter19/news1.txt");
            // read()方法抛出IOExcetion
            while ((readData = fileInputStream.read()) != -1) {
                // 读取utf-8格式保存的文本文件，中文3个字节编码，这样逐个字节读取肯定有乱码
                System.out.print((char)readData); // 转成char显示
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放文件输入流资源，抛出IOExcetion
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void readFile02() {
        FileInputStream fileInputStream = null;
        byte[] b = new byte[8];
        int readLength = 0;

        try {
            // new FileInputStream抛出FileNotFountException
            fileInputStream = new FileInputStream("e:/Java_practice/HSP_practice/chapter19/news1.txt");
            // read()方法抛出IOExcetion
            while ((readLength = fileInputStream.read(b)) != -1) {
                // 读取utf-8格式保存的文本文件，中文3个字节编码，这样逐个字节读取肯定有乱码
                System.out.print(new String(b, 0, readLength));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放文件输入流资源，抛出IOExcetion
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
