package com.td.kira.lab1ex5newloginregister;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
    public static String userBuget;

    public  UserProfile(){

    }

    public UserProfile(String userAge, String userEmail, String userName, String userBuget) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBuget = userBuget;
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

    public static String getUserBuget() {
        return userBuget;
    }

    public void setUserBuget(String userBuget) {
        this.userBuget = userBuget;
    }
}

