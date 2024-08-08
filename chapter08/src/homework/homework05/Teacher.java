package homework.homework05;

public class Teacher extends Employee{
    private int classDays;
    private double classSal;

    public Teacher(String name, double sal, int classDays, double classSal) {
        super(name, sal);
        this.classDays = classDays;
        this.classSal = classSal;
    }

    @Override
    public void printSal() {
        System.out.println("教师" + getName() + "的工资是：" + (getSal() + classSal * classDays));
    }
}
