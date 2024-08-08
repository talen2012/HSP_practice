package homework.homework04;

public class Manager extends Employee{
    private double bonus;

    public Manager(String name, double salaryOneDay, int workDays, double grade, double bonus) {
        super(name, salaryOneDay, workDays, grade);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public void printSalary() {
        System.out.print("经理" + getName() + "的工资是：" + (bonus + getDaySal() * getWorkDays() * getGrade()));
    }
}
