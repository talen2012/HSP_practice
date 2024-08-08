package com.talen.debug_;

import java.util.Arrays;

public class Debug01 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += i;
            System.out.println("i = " + i);
            System.out.println("sum = " + sum);
        }
        System.out.println("end...");

        int[] arr = {1, -1, 10, -20, 100};
        Arrays.sort(arr); //要使用alt+shift+F7强制进入方法或者调整settings->Build, Execution, Deployment->Debugger->stepping->Do not step into the classes, 取消勾选Java.*和Javax.*
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "\t");
        }
    }
}
