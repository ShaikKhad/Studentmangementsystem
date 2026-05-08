package com.sms.model;

public class Student {

    private int id;
    private String name;
    private int age;
    private String email;
    private String course;
    private double gpa;

    // Constructor
    public Student(int id, String name, int age, String email, String course, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.gpa = gpa;
    }

    // Getters
    public int getId()        { return id; }
    public String getName()   { return name; }
    public int getAge()       { return age; }
    public String getEmail()  { return email; }
    public String getCourse() { return course; }
    public double getGpa()    { return gpa; }

    // Setters
    public void setName(String name)     { this.name = name; }
    public void setAge(int age)          { this.age = age; }
    public void setEmail(String email)   { this.email = email; }
    public void setCourse(String course) { this.course = course; }
    public void setGpa(double gpa)       { this.gpa = gpa; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-3d | %-25s | %-15s | %-4.2f |",
                id, name, age, email, course, gpa);
    }
}
