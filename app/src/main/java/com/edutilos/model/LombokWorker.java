package com.edutilos.model;

import java.io.Serializable;

//@Data
public class LombokWorker implements Serializable {
    private long id;
    private String name;
    private String password;
    private int age;
    private double wage;
    private boolean active;

    public LombokWorker(long id, String name, String password, int age, double wage, boolean active) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public LombokWorker(String name, String password, int age, double wage, boolean active) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public LombokWorker() {
    }
}
