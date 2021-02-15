package models;

import javax.validation.constraints.*;

public class Person {
//    @NotEmpty(message = "Это поле не должно быть пустым")
    private int id;

    @NotEmpty(message = "Это поле не должно быть пустым")
    @Size(min=2, max = 30, message ="Имя должно содержать от 2-ух до 30-ти символов")
    private String name;

    @Min(value = 0, message = "Возраст должен быть положительным значением")
    @Max(value = 250, message = "Возраст не должен быть больше 250")
    private int age;

    @NotNull(message = "Выберите пол")
    boolean sex;

    // без этого конструктора аннотация @ModelAttribute может создать объект Person только если введены все параметры
    public Person() {

    }

    public Person(String name, int age, boolean sex) {
        this.id = 1;
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
