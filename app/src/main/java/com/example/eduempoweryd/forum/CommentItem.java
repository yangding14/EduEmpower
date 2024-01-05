package com.example.eduempoweryd.forum;

public class CommentItem {
    private String author;
    private String comment;

    // Default constructor required for Firebase
    public CommentItem() {
    }

    public CommentItem(String author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return comment;
    }

    public void setContent(String comment) {
        this.comment = comment;
    }
}
