package com.example.motbot.demonhacks;


public class User {
    private String username;
    private String password;
    private Tower t;
    //Constructor
    public User(String usr, String pwrd) {
        username = usr;
        password = pwrd;
    }
    //Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Tower getT() {
        return t;
    }
    public void setT(Tower t) {
        this.t = t;
    }
    //Methods
}