package org.chirkov.firstSpringMvcProject.models;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3,max = 33, message = "First name should 3-33 characters long!")
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 3,max = 33, message = "Surname should 3-33 characters long!")
    private String surname;
    @Size(min = 1,max = 333, message = "Age should be between 0 - 333 and ")
    private int age;
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Email should be valid")
    private String email;


    public Person(int id,String name, String surname, int age, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public Person() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
