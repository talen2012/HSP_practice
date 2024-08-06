public class Homework13 {
    public static void main(String[] args) {
        Circle2 c2 = new Circle2();
        PassObjects po = new PassObjects();
        po.printArea(c2, 5);
    }
}

class PassObjects {
    public void printArea(Circle2 c2, int times) { // 打印1-times的每个整数半径值，并输出对应的圆面积
        System.out.println("radius\tarea");
        for (int i = 1; i <= times; i++) {
            c2.radius = i;
            System.out.println(c2.radius + "\t\t" + c2.finArea());
        }
    }
}

class Circle2 { // 返回findArea返回圆的面积
    double radius;

    public double finArea() {
        return Math.PI * radius * radius;
    }
}
