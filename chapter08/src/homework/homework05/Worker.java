package homework.homework05;

public class Worker extends Employee {
    public Worker(String name, double sal) {
        super(name, sal);
    }

    @Override
    public void printSal() {
        System.out.print("工人");
        super.printSal();
    }
}
