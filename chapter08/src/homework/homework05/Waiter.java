package homework.homework05;

public class Waiter extends Employee{
    public Waiter(String name, double sal) {
        super(name, sal);
    }

    @Override
    public void printSal() {
        System.out.print("服务员");
        super.printSal();
    }
}
