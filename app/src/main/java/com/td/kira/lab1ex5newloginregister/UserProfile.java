package com.td.kira.lab1ex5newloginregister;

import java.util.Map;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
    static public String userBuget;
    private Map<String,Double> GpsData;


    public  UserProfile(){

    }



    public UserProfile(String userAge, String userEmail, String userName, String userBuget ) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBuget = userBuget;

    }

    public UserProfile(String userAge, String userEmail, String userName, String userBuget, Map<String,Double> GpsData){
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBuget = userBuget;
        this.GpsData = GpsData;
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

    public void setGpsData(Map<String, Double> gpsData) {
        GpsData = gpsData;
    }

    public Map<String, Double> getGpsData() {
        return GpsData;
    }
}

