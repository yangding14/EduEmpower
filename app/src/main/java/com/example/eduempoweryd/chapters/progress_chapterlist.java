package com.example.eduempoweryd.chapters;

class progress_chapterlist {
    String position;
    String name;
    int image ;


    public progress_chapterlist(String chapterno, String chaptername, int image) {
        this.position = chapterno;
        this.name = chaptername;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getPosition() {
        return position;
    }
}
