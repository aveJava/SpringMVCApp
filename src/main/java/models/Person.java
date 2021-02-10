package models;

public class Person {
    private static int person_COUNT;   // счетчик для автоинкрементирования id
    private int id;
    private String name;
    private int age;
    boolean sex;

    public Person(String name, int age, boolean sex) {
        this.id = ++person_COUNT;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}