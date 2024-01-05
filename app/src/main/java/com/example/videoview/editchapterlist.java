package com.example.videoview;

class editchapterlist {
    String position;
    String name;
    String filetype;
    String key;

    public editchapterlist(String position, String name ) {
        this.position = position;
        this.name = name;
    }

    public editchapterlist(String position, String name , String filetype , String key) {
        this.position = position;
        this.name = name;
        this.filetype = filetype ;
        this.key = key ;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getFileType() {
        return filetype;
    }

    public String getKey() {return key;}
}
