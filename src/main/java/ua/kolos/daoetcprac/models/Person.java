package ua.kolos.daoetcprac.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    //вид валидации, в мёсседжи указывается что будет показано пользоватею при несоблюдению валидации
    @NotEmpty(message="name should not be empty")
    @Size(min = 2, max = 30, message = "too long or to short")//должно занимать от 2 до 30 сиволов
    private String name;

    //утанавливает минимальное значение для числового поля
    @Min(value = 0, message = "you are still not were born")


    private int age;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    private String email;

    public Person(){

    }
    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
