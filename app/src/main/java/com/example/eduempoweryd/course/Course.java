package com.example.eduempoweryd.course;

import java.util.List;

public class Course {
    private String courseId;
    private String courseTitle;
    private String courseDesc;
    private String category;
    private String uri;

    public Course() {
    }

    public Course(String courseId, String courseTitle, String courseDesc, String category, String uri) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDesc = courseDesc;
        this.category = category;
        this.uri = uri;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
