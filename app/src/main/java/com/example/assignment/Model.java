package com.example.assignment;

public class Model {
    private String imageUrl;
    private String courseTitle;
    private String courseDesc;
    private String category;
    private boolean isEditCourse;
    public Model() {

    }
    public boolean isEditCourse() {
        return isEditCourse;
    }
    public Model(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }


    public String getCourseTitle() {
        return courseTitle;
    }
    public String getCourseDesc() {
        return courseDesc;
    }
    public String getCategory() {
        return category;
    }
}
