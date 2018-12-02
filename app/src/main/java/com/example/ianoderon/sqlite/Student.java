package com.example.ianoderon.sqlite;

public class Student {
    String fname, lname;
    Long points;

    public Student() {

    }

    public Student(String fname, String lname, Long points) {
        this.fname = fname;
        this.lname = lname;
        this.points = points;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Long getPoints() {
        return points;
    }
}
