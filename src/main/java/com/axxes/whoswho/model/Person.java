package com.axxes.whoswho.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id
    private String id;
    @Column
    private String givenName;
    @Column
    private String surname;
    @Column
    private String lastName;
    @Column
    private Sex sex;

    public Person(){

    }

    public Person(String id, String givenName, String surname, String lastName, Sex sex) {
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.lastName = lastName;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
