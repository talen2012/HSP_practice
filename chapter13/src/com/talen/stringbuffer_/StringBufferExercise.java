package com.talen.stringbuffer_;

public class StringBufferExercise {
    public static void main(String[] args) {
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.out.println(sb);

        StringBuffer sb2 = new StringBuffer(str);
        System.out.println(sb2);
    }
}
