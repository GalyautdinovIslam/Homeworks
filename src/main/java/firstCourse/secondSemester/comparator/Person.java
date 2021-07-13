package firstCourse.secondSemester.comparator;

import java.util.Objects;

public class Person implements Comparable<Person> {
    protected String name;
    protected int age;
    protected boolean sex;

    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public int compareTo(Person person) {
        if (this.name.compareTo(person.name) == 0) return this.age - person.age;
        else return this.name.compareTo(person.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return this.age == person.age &&
                this.sex == person.sex &&
                Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(name, age, sex);
    }

    @Override
    public String toString() {
        return "Person: " + this.name + ", " + this.age + " y.o. " + (this.sex ? "Male." : "Female.");
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isSex() {
        return this.sex;
    }

}
