package com.example.lab.stablematching;

public class userbase {
private String email;
private String name_of_the_user;
public userbase(){}
public userbase(String email,String name_of_the_user){
    this.email=email;
    this.name_of_the_user=name_of_the_user;

    }

    public String getEmail() {
        return email;
    }

    public String getName_of_the_user() {
        return name_of_the_user;
    }
}
