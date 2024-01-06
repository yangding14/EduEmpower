package com.example.eduempoweryd.chapters;

public class CompletedCourse {

    String cc_courseName;
    String cc_courseDesc;
    int cc_courseImage;

    public CompletedCourse(String cc_courseName, String cc_courseDesc, int cc_courseImage) {
        this.cc_courseName = cc_courseName;
        this.cc_courseDesc = cc_courseDesc;
        this.cc_courseImage = cc_courseImage;
    }

    public String getCc_courseName() {
        return cc_courseName;
    }

    public String getCc_courseDesc() {
        return cc_courseDesc;
    }

    public int getCc_courseImage() {
        return cc_courseImage;
    }
}
