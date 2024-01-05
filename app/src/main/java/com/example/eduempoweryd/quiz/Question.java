package com.example.eduempoweryd.quiz;

import java.util.List;

public class Question {
    private String questionId;
    private String question;
    private List<String> options;
    private String courseId;

    public Question() {
    }

    public Question(String questionId, String question, List<String> options, String courseId) {
        this.questionId = questionId;
        this.question = question;
        this.options = options;
        this.courseId = courseId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
