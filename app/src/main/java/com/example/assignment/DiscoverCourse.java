package com.example.assignment;

public class DiscoverCourse {

    String courseName;
    String courseDesc;
    int courseImage;

    public DiscoverCourse(String courseName, String courseDesc, int courseImage) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.courseImage = courseImage;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public int getCourseImage() {
        return courseImage;
    }
}
