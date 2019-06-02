package com.example.newcoder.model;


import java.io.Serializable;

public class Question implements Serializable
{
    private int id;//问题id
    private String title;//问题标题
    private int answerid;//答案id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

}
