package org.chirkov.firstSpringMvcProject.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3,max = 33, message = "First name should 3-33 characters long!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 3,max = 33, message = "Surname should 3-33 characters long!")
    @Column(name = "surname")
    private String surname;
    @Range(min = 1,max = 333, message = "Age should be between 1 - 333 and ")
    @Column(name = "age")
    private int age;
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;
    // Страна, Город, индекс(6 цифр)
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format : 'Country, City, Postal code (6 digits)'")
    @Column(name = "address")
    private String address;


    public Person(String name, String surname, int age, String email, String address) {
//        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
