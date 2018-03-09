package com.wirtec.rparayno.examify.ClassActivity;

/**
 * Created by rparayno on 09/03/2018.
 */

public class ClassCard {
    private String className;
    private int thumbnail;

    public ClassCard(){

    }

    public ClassCard(String className, int thumbnail) {
        this.className = className;
        this.thumbnail = thumbnail;
    }

    public String getClassName() {
        return className;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
