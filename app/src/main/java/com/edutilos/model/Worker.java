package com.edutilos.model;

import java.io.Serializable;

/**
 * Created by edutilos on 02.11.18.
 */

public class Worker implements Serializable {
    private long id;
    private String name;
    private String password;
    private int age;
    private double wage;
    private boolean active;

    public Worker(long id, String name, String password, int age, double wage, boolean active) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public Worker(String name, String password, int age, double wage, boolean active) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public Worker() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                ", active=" + active +
                '}';
    }
}
