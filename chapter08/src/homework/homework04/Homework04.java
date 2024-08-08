package homework.homework04;

public class Homework04 {
    public static void main(String[] args) {
        Worker jack = new Worker("jack", 300.0, 200, 1.0);
        jack.printSalary();
        Manager simith = new Manager("simith", 500, 300, 1.2, 30000);
        simith.printSalary();
    }
}
