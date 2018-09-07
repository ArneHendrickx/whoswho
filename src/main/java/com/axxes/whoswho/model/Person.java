package com.axxes.whoswho.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id
    private String id;
    @Column
    private String givenName;
    @Column
    private String surName;
    @Column
    private String displayName;
    @Column(name = "sex")
    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    public Person(){

    }

    public Person(String id, String givenName, String surname, String lastName, Sex sex) {
        this.id = id;
        this.givenName = givenName;
        this.surName = surname;
        this.displayName = lastName;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
