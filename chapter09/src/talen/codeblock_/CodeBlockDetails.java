package talen.codeblock_;

public class CodeBlockDetails {
    public static void main(String[] args) {
        new BB();
    }
}

class AA {
    {
        System.out.println("AA的普通代码块被调用");
    }
    public int n1 = getVal1();
    public int getVal1() {
        System.out.println("AA的普通属性被初始化");
        return 1;
    }

    public static int n2 = getVal2();
    public static int getVal2() {
        System.out.println("AA的静态属性被初始化");
        return 2;
    }
    static {
        System.out.println("AA的静态代码块被调用");
    }

    public AA() {
        System.out.println("AA的无参构造被调用");
    }

    // 父类有一个私有方法
    private void hi() {};
}

class BB extends AA {

    static {
        System.out.println("BB的静态代码块被调用");
    }
    public static int n4 = getVal4();
    public static int getVal4() {
        System.out.println("BB的静态属性被初始化");
        return 4;
    }

    public int n3 = getVal3();
    public int getVal3() {
        System.out.println("BB的普通属性被初始化");
        return 3;
    }

    {
        System.out.println("BB的普通代码块被调用");
    }

    public BB() {
        System.out.println("BB的无参构造被调用");
    }

    public void hi() {
        System.out.println("子类的hi()");
    }
}
