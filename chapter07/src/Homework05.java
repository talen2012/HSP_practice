public class Homework05 {
    public static void main(String[] args) {
        Circle c1 = new Circle(3);
        c1.showCircumference();
        c1.showArea();
    }
}

class Circle {
    double radius;

    public Circle(double r) {
        this.radius = r;
    }

    public void showCircumference() {
        System.out.println("周长是：" + 2 * Math.PI * radius);
    }

    public void showArea() {
        System.out.println("面积是：" + Math.PI * radius * radius);
    }
}
