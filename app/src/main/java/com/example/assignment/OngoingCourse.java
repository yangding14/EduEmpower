package com.example.assignment;

public class OngoingCourse {

    String oc_courseName;
    String oc_courseQty;
    int oc_courseImage;

    public OngoingCourse(String oc_courseName, String oc_courseQty, int oc_courseImage) {
        this.oc_courseName = oc_courseName;
        this.oc_courseQty = oc_courseQty;
        this.oc_courseImage = oc_courseImage;
    }

    public String getOc_courseName() {
        return oc_courseName;
    }

    public String getOc_courseQty() {
        return oc_courseQty;
    }

    public int getOc_courseImage() {
        return oc_courseImage;
    }
}
