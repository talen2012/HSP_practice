package com.talen.try_;

public class TryCatchDetails {
    public static void main(String[] args) {
        try {
            String str = "12ff4";
            int a = Integer.parseInt(str);
            System.out.println("数字=" + a);
        } catch (NumberFormatException e) {
            System.out.println("异常信息=" + e.getMessage());;
        }
    }
}
