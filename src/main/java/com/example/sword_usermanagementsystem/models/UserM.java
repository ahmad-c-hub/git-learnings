package com.example.sword_usermanagementsystem.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "userm")
public class UserM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String job_title;
    private String linked_in;
    private LocalDate dob;
    private String role;
    private Float salary;

    public UserM(String username, String email, String job_title, LocalDate dob, String linked_in, String password, String role, Float salary) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.job_title = job_title;
        this.dob = dob;
        this.linked_in = linked_in;
        this.role = role;
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getJob_title() {
        return job_title;
    }

    public LocalDate getDob() {
        return dob;
    }

    public UserM() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.username = name;
    }

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", job_title='" + job_title + '\'' +
                ", dob=" + dob +
                ", linked_in='" + linked_in + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getLinked_in() {
        return linked_in;
    }

    public void setLinked_in(String linked_in) {
        this.linked_in = linked_in;
    }
}
