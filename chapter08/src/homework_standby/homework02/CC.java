package homework_standby.homework02;

import homework.homework02.AA;

public class CC extends AA {
    public void show() {
        System.out.println(n1 + n2); // 不同包下的子类，可以访问父类public和protected的属性
    }
}
