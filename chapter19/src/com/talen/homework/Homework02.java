package com.talen.homework;

import java.io.*;

public class Homework02 {
    // 使用BufferedReader为一个文本文件加上行号
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line;
        try {
            br = new BufferedReader(new FileReader("e:/Java_practice/HSP_practice/chapter19/a.txt"));
            bw = new BufferedWriter(new FileWriter("e:/Java_practice/HSP_practice/chapter19/a_with_no.txt"));
            int i = 1;
            while ((line = br.readLine()) != null) {
                bw.write(i + " " + line);
                bw.newLine();
                i++;
            }
            System.out.println("加行号成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
