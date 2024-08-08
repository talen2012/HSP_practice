package homework.homework02;

public class BB extends AA {
    public void show() {
        System.out.println(n1 + n2 + n3); // 同包下的子类，可以访问父类public，protected和默认的属性
    }
}
