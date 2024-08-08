package homework_standby.homework02;

import homework.homework02.AA;

public class Homework02Standby {
    public static void main(String[] args) {
        AA aa = new AA();
        System.out.println(aa.n1); // 不同包下的非子类，只有public属性可以访问
    }
}
