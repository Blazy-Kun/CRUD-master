package com.example.crud;

public class User {

    String id;
    String username;

    String password;
    String user;
    public User(){

    }



    public User(String id, String username, String password,String user) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
