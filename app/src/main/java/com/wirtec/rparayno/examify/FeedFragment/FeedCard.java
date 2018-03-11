package com.wirtec.rparayno.examify.FeedFragment;

/**
 * Created by Richard on 03/12/2018.
 */

public class FeedCard {
    private String username;
    private int thumbnail;
    private String userstatus;
    private String content;


    public FeedCard(String username, String userstatus, String content, int thumbnail) {
        this.username = username;
        this.userstatus = userstatus;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
