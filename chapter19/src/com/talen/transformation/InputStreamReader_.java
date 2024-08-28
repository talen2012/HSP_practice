package com.talen.transformation;

import java.io.*;

public class InputStreamReader_ {
    public static void main(String[] args) throws IOException {
        String filePath = "e:/Java_practice/HSP_practice/chapter19/a_gbk.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));
        System.out.println(br.readLine());
        br.close();
    }
}
