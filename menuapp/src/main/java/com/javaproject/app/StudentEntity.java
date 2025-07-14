package com.javaproject.app;

import jakarta.persistence.*;

@Entity // Tells Hibernate this is an entity class
@Table(name = "studentdetails") // Table name in PostgreSQL
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String course;
    private double marks;

    // Default constructor required by Hibernate
    public StudentEntity() {}

    // Constructor to create student quickly
    public StudentEntity(String name, int age, String course, double marks) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }
}
