package com.talen.outputstream_;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputStream_ {
    public static void main(String[] args) {
    }
    @Test
    public void writeFile01() {
        String path = "e:/Java_practice/HSP_practice/chapter19/a.txt";
        FileOutputStream fileOutputStream = null;
        try {
            // 文件不存在会创建，但目录必须存在
            // 当是一个目录不是文件，或者文件打不开，抛出FileNotFoundException
            // fileOutputStream = new FileOutputStream(path, true); //覆盖写
            fileOutputStream = new FileOutputStream(path, true); // 追加写
            // 方式1，写入一个字节write(int), 虽然输入是一个int，但当作字节处理
//            fileOutputStream.write('T');
            // 方式2，写入一个char[]
            String str = "talen田磊";
//            fileOutputStream.write(str.getBytes());
            // 方式3，写入一个char[]的指定部分
            fileOutputStream.write(str.getBytes(), 5,6);
            System.out.println("写入成功...");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}