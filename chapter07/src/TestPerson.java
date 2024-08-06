public class TestPerson {
    public static void main(String[] args) {
        Person p1 = new Person("mary", 12);
        Person p2 = new Person("mary", 12);
        System.out.println(p1.compareTo(p2));
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    boolean compareTo(Person p) {
        if (name.equals(p.name) && age == p.age) {
            return true;
        } else {
            return false;
        }
    }
}
