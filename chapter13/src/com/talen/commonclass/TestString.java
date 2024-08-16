package com.talen.commonclass;

public class TestString {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = new String("talen");
        String s3 = s1 + s2;
        String s4 = "hellotalen";
        System.out.println(s3 == s4);
        String s6 = "32" + "324";
    }
}
