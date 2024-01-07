package com.example.eduempoweryd.course;

class progress_chapterlist {
    String position;
    String name;
    int image ;
    String key ;


    public progress_chapterlist(String chapterno, String chaptername, int image , String key) {
        this.position = chapterno;
        this.name = chaptername;
        this.image = image;
        this.key = key ;
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
