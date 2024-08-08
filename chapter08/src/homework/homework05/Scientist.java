package homework.homework05;

public class Scientist extends Employee{
    private double bonus;

    public Scientist(String name, double sal, double bonus) {
        super(name, sal);
        this.bonus = bonus;
    }

    @Override
    public void printSal() {
        System.out.println("科学家" + getName() + "的工资是：" + (getSal() + bonus));
    }
}
