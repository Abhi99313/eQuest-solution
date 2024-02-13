package com.example.mvvm;

public class Users {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private String avatarUrl;

    public Users(int id, String first_name, String last_name,String gender, String email, String avatarUrl) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstname) {
        this.first_name = first_name;
    }

    public void setLastName(String lastname){ this.last_name= last_name;}

    public String getLastName(){ return last_name;}

    public void setGender(String gen){this.gender=gender;}
    public String getGender(){ return gender;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}

