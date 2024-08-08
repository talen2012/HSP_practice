package homework.homework05;

public class Homework05 {
    public static void main(String[] args) {
        Worker jack = new Worker("jack", 100000);
        jack.printSal();
        Peasant simth = new Peasant("simth", 50000);
        simth.printSal();
        Waiter scott = new Waiter("scott", 80000);
        scott.printSal();

        Teacher loli = new Teacher("loli", 60000, 30, 100);
        loli.printSal();

        Scientist newton = new Scientist("newton", 150000, 50000);
        newton.printSal();
    }
}
