package homework.homework04;

public class Worker extends Employee{
    public Worker(String name, double salaryOneDay, int workDays, double grade) {
        super(name, salaryOneDay, workDays, grade);
    }

    @Override
    public void printSalary() {
        System.out.print("普通员工" + getName() + "的工资是：");
        super.printSalary();
    }
}
