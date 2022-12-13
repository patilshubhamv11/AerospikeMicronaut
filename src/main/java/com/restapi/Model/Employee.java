package com.restapi.Model;

import com.aerospike.mapper.annotations.*;



@AerospikeRecord(namespace= "test",set="Employee")
public class Employee {
    @AerospikeKey
    @AerospikeBin
    private int id;
    @AerospikeBin
    private String name;
    @AerospikeBin
    private String email;
    @AerospikeBin
    private int sal;
    @AerospikeBin
    private int age;
    @AerospikeBin
    private String JoiningDate;
    @AerospikeEmbed
    @AerospikeBin
    private Department department;

    public Employee(int id, String name, String email, int sal, int age, String joiningDate, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sal = sal;
        this.age = age;
        JoiningDate = joiningDate;
        this.department = department;
    }

    public Employee() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        JoiningDate = joiningDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sal=" + sal +
                ", age=" + age +
                ", JoiningDate='" + JoiningDate + '\'' +
                ", department=" + department +
                  '}';
    }
}