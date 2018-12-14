package com.example.lab.stablematching;

import java.util.ArrayList;
import java.util.List;

public class userbase {
private String email;
private String name_of_the_user;
private String role;
private boolean table;
List<String> plist;
public userbase(){}
public userbase(String email,String name_of_the_user,String role){
    this.email=email;
    this.name_of_the_user=name_of_the_user;
    this.role=role;
    }

    public String getEmail() {
        return email;
    }

    public String getName_of_the_user() {
        return name_of_the_user;
    }

    public String getRole() {
        return role;
    }

    public boolean isTable() {return table;}

    public void setTable(boolean table) {this.table = table; }

    public List<String> getPlist() {return plist;}

    public void setPlist(List<String> plist) {this.plist = plist;}
}
