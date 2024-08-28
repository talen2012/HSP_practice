package com.talen.homework;

import java.io.File;
import java.io.IOException;

public class Homework01 {
    /**
     * 判断是否有文件夹mytemp, 没有就创建
     * 在该文件夹判断有没有hello.txt文件，没有就创建
     * 有就提示已存在
     * @param args
     */
    public static void main(String[] args) {
        File parentDir = new File("e:/Java_practice/HSP_practice/chapter19/myTemp");
        if (!parentDir.exists()) {
            if (parentDir.mkdir()) {
                System.out.println(parentDir.getAbsolutePath() + "创建成功");
            } else {
                System.out.println(parentDir.getAbsolutePath() + "创建失败");
            }
        } else {
            System.out.println(parentDir.getAbsolutePath() + "已存在");
        }

        File helloFile = new File(parentDir, "hello.txt");

        if (!helloFile.exists()) {
            try {
                helloFile.createNewFile();
                System.out.println("hello.txt文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("hello.txt文件已存在");
        }

    }
}
