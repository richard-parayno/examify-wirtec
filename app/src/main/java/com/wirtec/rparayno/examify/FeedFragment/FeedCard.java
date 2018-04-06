package com.wirtec.rparayno.examify.FeedFragment;

/**
 * Created by Richard on 03/12/2018.
 */

public class FeedCard {
    private String username;
    private String fbId;
    private String userstatus;
    private String content;


    public FeedCard(String username, String userstatus, String content, String fbId) {
        this.username = username;
        this.userstatus = userstatus;
        this.content = content;
        this.fbId = fbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThumbnail() {
        return fbId;
    }

    public void setThumbnail(String thumbnail) {
        this.fbId = thumbnail;
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
