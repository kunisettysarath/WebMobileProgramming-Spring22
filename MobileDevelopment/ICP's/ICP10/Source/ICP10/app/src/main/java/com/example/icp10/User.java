package com.example.icp10;

import com.google.gson.annotations.SerializedName;
//user class with parameters user id and user name
public class User {
    private int id;
    @SerializedName("login")
    private String username;
//    methods to retrive the user id and username
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;

    }
}
