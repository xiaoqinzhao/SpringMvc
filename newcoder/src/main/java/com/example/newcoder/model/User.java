package com.example.newcoder.model;

public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHead_url(String headurl) {
        this.headurl = headurl;
    }

    private int id;
    private String name;
    private String password;
    private String salt;
    private String headurl;
}
