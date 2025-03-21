package com.pinnacle.student.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

@Entity
@Table(name = "students") // Assuming you want a table named 'students'
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "college")
    private String college;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "pcontact")
    private String pcontact;

    @Column(name = "course")
    private String course;

    @Column(name = "fees")
    private Double fees;

    @Column(name = "pfees")
    private Double pfees;

    // bfees will be derived from fees - pfees, no need to store it directly
    @Transient // This annotation tells JPA not to store this field in the database
    private Double bfees;

    // No-arg constructor
    public Student() {
    }

    // All-args constructor
    public Student(Long id, String name, String college, String address, String contact, String pcontact,
                   String course, Double fees, Double pfees, Double bfees) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.address = address;
        this.contact = contact;
        this.pcontact = pcontact;
        this.course = course;
        this.fees = fees;
        this.pfees = pfees;
        this.bfees = bfees;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPcontact() {
        return pcontact;
    }

    public void setPcontact(String pcontact) {
        this.pcontact = pcontact;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Double getPfees() {
        return pfees;
    }

    public void setPfees(Double pfees) {
        this.pfees = pfees;
    }

    // bfees is calculated as fees - pfees
    public Double getBfees() {
        if (fees != null && pfees != null) {
            return fees - pfees;
        }
        return 0.0; // Return 0.0 if fees or pfees is null
    }
}
