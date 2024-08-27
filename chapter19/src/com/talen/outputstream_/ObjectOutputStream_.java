package com.talen.outputstream_;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStream_ {
    public static void main(String[] args) throws IOException {
        String filePath = "e:/Java_practice/HSP_practice/chapter19/data.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));

        oos.write(306); // FileOutputStream的原始方法，写入一个字节，虽然传入的是一个int，当作byte
        oos.writeInt(306);
        oos.writeBoolean(true);
        oos.writeChar('a');
        oos.writeDouble(9.5);
        oos.writeUTF("韩顺平教育");

        // 保存一个Dog对象
        oos.writeObject(new Dog("小黄", 2));
        oos.close();
        System.out.println("数据保存完毕(序列化形式)...");
    }
}

