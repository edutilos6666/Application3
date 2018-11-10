package com.edutilos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MongoWorker implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("wage")
    private double wage;
    @SerializedName("active")
    private boolean active;

    public MongoWorker(String name, int age, double wage, boolean active) {
        this.name = name;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public MongoWorker() {
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
        return "MongoWorker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                ", active=" + active +
                '}';
    }
}
