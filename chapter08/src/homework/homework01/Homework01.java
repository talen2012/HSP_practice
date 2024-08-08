package homework.homework01;

public class Homework01 {
    public static void main(String[] args) {
        Person[] peoples = new Person[3];
        peoples[0] = new Person("jack", 20, "student");
        peoples[1] = new Person("smith", 40, "teacher");
        peoples[2] = new Person("jason", 35, "worker");

        System.out.println("排序前：");
        for (int i = 0; i < peoples.length; i++) {
            System.out.println(peoples[i]);
        }
        Person temp = null;
        for (int i = 0; i < peoples.length - 1; i++) {
            for (int j = 0; j < peoples.length - 1 - i; j++) {
                if (peoples[j].getAge() < peoples[j + 1].getAge()) {
                    temp = peoples[j];
                    peoples[j] = peoples[j + 1];
                    peoples[j + 1] = temp;
                }
            }
        }

        System.out.println("排序后：");
        for (int i = 0; i < peoples.length; i++) {
            System.out.println(peoples[i]);
        }
    }
}

class Person {
    private String name;
    private int age;
    private String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}