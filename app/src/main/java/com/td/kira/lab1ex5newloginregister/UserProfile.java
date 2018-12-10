package com.td.kira.lab1ex5newloginregister;

import java.util.Map;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
    static public String userBuget;
    private double lat;
    private double lon;


    public  UserProfile(){

    }



    public UserProfile(String userAge, String userEmail, String userName, String userBuget ) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBuget = userBuget;

    }

    public UserProfile(String userAge, String userEmail, String userName, String userBuget, double lon, double lat){
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBuget = userBuget;
        this.lon = lon;
        this.lat = lat;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    static public String getUserBuget() {
        return userBuget;
    }

    public void  setUserBuget(String userBuget) {
        this.userBuget = userBuget;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}

