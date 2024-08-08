package homework.homework05;

public class Peasant extends Employee{
    public Peasant(String name, double sal) {
        super(name, sal);
    }

    @Override
    public void printSal() {
        System.out.print("农民");
        super.printSal();
    }
}
