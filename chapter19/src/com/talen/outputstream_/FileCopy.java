package com.talen.outputstream_;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;
        byte[] buf = new byte[1024];
        int readLen = 0;

        String fileInPath = "e:/Java_practice/HSP_practice/chapter19/bg.png";
        String fileOutPath = "e:/Java_practice/HSP_practice/chapter19/bg/bg.png";

        try {
            fileIn = new FileInputStream(fileInPath);
            fileOut = new FileOutputStream(fileOutPath);
            while ((readLen = fileIn.read(buf)) != -1) {
                fileOut.write(buf, 0, readLen);
            }
            System.out.println("文件拷贝成功...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileIn != null) {
                    fileIn.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}
