package com.wirtec.rparayno.examify;

/**
 * Created by rparayno on 12/03/2018.
 */

public class User {
    //user creds
    private int thumbNail;
    private String userName;
    private String firstName;
    private String passWord;

    private String[] currentClasses;

    // user stats
    private int medals;
    private String lastOnline;
    private String bestClass;
    private double currentXP;


    //first init
    public User(String userName, String firstName, int thumbNail) {
        this.userName = userName;
        this.firstName = firstName;
        this.thumbNail = thumbNail;
        this.currentXP = 0;
    }

    public int getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(int thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(double currentXP) {
        this.currentXP = currentXP;
    }

    public String[] getCurrentClasses() {
        return currentClasses;
    }

    public void setCurrentClasses(String[] currentClasses) {
        this.currentClasses = currentClasses;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }

    public String getBestClass() {
        return bestClass;
    }

    public void setBestClass(String bestClass) {
        this.bestClass = bestClass;
    }
}
