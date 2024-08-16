package com.talen.stringbuffer_;

public class StringBufferExercise2 {
    public static void main(String[] args) {
        // 输入商品的名称和价格，按如下格式输出
        // 商品名 商品价格
        // 手机   123,564.59
        Double price = 25252123564.594535;
        String pricePrint = String.format("%.2f", price);
        StringBuffer sb = new StringBuffer(pricePrint);

        for (int i = sb.lastIndexOf(".") - 3; i > 0; i -= 3) {
            sb.insert(i, ",");
        }
        System.out.println(sb);
    }
}