package homework.homework02;

public class Homework02 {
    public static void main(String[] args) {
        AA aa = new AA();
        BB bb = new BB();

        System.out.println(aa.n1 + aa.n2 + aa.n3); // 同包下的非子类，可以访问public, protected, 默认属性
    }
}



