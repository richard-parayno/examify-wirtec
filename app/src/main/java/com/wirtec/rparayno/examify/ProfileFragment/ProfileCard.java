package com.wirtec.rparayno.examify.ProfileFragment;

import com.wirtec.rparayno.examify.User;

/**
 * Created by rparayno on 12/03/2018.
 */

public class ProfileCard {
    private String bestClass;
    private String lastOnline;
    private int medals;
    private int type;
    private double currentXP;


    public ProfileCard(User user) {
        this.bestClass = user.getBestClass();
        this.lastOnline = user.getLastOnline();
        this.medals = user.getMedals();
        this.currentXP = user.getCurrentXP();
    }

    public String getBestClass() {
        return bestClass;
    }

    public void setBestClass(String bestClass) {
        this.bestClass = bestClass;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public double getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(double currentXP) {
        this.currentXP = currentXP;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
