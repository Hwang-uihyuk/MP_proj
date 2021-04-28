package com.example.mp_proj;

public class ChatModule {
    String name;
    String content;
    String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public ChatModule(String name, String content) {
        this.name = name;
        this.content = content;
    }
    public ChatModule(){

    }




}
