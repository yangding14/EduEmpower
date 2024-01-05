package com.example.eduempoweryd.quiz;

public class QuestionAttempt {
    private String quizId;
    private String question;
    private String correctAnswer;
    private String chosenAnswer;
    private Boolean result;

    public QuestionAttempt() {
    }

    public QuestionAttempt(String quizId, String question, String correctAnswer, String chosenAnswer, Boolean result) {
        this.quizId = quizId;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.chosenAnswer = chosenAnswer;
        this.result = result;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(String chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
