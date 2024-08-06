public class Test {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        AA aa = new AA();
        aa.swap(a, b);
        System.out.println("main方法中a=" + a + " b=" + b);
    }
}

class AA {
    public void swap(int a, int b) {
        System.out.println("交换前：a=" + a + " b=" + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("交换后：a=" + a + " b=" + b);
    }
}
