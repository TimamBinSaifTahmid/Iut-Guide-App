package com.example.iutguide;

public class AddCourseFirebase {
    private  String courseName;
    private String courseId;
    private  String courseCredit;


    public AddCourseFirebase(){

    }
    public AddCourseFirebase(String courseName,String courseId,String courseCredit) {
        this.courseName = courseName;
        this.courseId=courseId;
        this.courseCredit=courseCredit;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseCId) {
        this.courseId = courseCId;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }
}
