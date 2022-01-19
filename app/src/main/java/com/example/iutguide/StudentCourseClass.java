package com.example.iutguide;

public class StudentCourseClass {

private String courseName;

public StudentCourseClass(){

}

    public StudentCourseClass(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
