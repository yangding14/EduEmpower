package com.example.eduempoweryd;

public class Attempt {
    private String attemptDate;
    private int questionSize;
    private String userId;

    public Attempt() {
    }

    public Attempt(String attemptDate, int questionSize) {
        this.attemptDate = attemptDate;
        this.questionSize = questionSize;
    }

    public String getAttemptDate() {
        return attemptDate;
    }

    public void setAttemptDate(String attemptDate) {
        this.attemptDate = attemptDate;
    }

    public int getQuestionSize() {
        return questionSize;
    }

    public void setQuestionSize(int questionSize) {
        this.questionSize = questionSize;
    }
}
