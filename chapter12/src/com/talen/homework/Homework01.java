package com.talen.homework;

public class Homework01 {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new ArrayIndexOutOfBoundsException("参数个数不对");
            }
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            System.out.println("计算结果是：" + cal(num1, num2));
        } catch (NumberFormatException e) {
            System.out.println("参数的格式不对");
        } catch (ArithmeticException e) {
            System.out.println("除0错误");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(true?2:3.0);
    }

    // 默认抛出除0异常
    public static int cal(int n1, int n2) {
        return n1 / n2;
    }
}
