package com.example.eduempoweryd.chapter;

class chapterlist {
    String position;
    String name;
    int image ;

    String key ;
    String file ;
    public chapterlist(String chapterno, String chaptername, int image , String key) {
        this.position = chapterno;
        this.name = chaptername;
        this.image = image;
        this.key = key ;
    }

    public chapterlist(String chapterno, String chaptername , String file){
        this.position = chapterno;
        this.name = chaptername;
        this.file = file ;
    }

    public chapterlist(){
        
    }

    public String getPosition(){return position;}

    public String getName(){return name;}
    public int getImage() {
        return image;
    }

    public String getKey() {return key;}
}
