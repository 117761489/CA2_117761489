package com.example.ca2_117761489;

public class StaffModel {

    private int id;
    private String name;
    private int age;
    private boolean isCurrent;

    //Constructors
    public StaffModel(int id, String name, int age, boolean isCurrent) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isCurrent = isCurrent;
    }

    public StaffModel() {
    }

    // toString method
    @Override
    public String toString() {
        return "StaffModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isCurrent=" + isCurrent +
                '}';
    }


    //Getters and Setters
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

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
