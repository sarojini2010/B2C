package com.example.thoughtchimp.freadom;

/**
 * Created by thoughtchimp on 7/14/2016.
 */
public class User {
    int imageResourceId;

    public String getSessiontitle() {
        return sessiontitle;
    }

    public void setSessiontitle(String sessiontitle) {
        this.sessiontitle = sessiontitle;
    }

    String sessiontitle;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    String sessionid;

    public String getParentnote() {
        return parentnote;
    }

    public void setParentnote(String parentnote) {
        this.parentnote = parentnote;
    }

    String parentnote;

    public String getSequencenumber() {
        return sequencenumber;
    }

    public void setSequencenumber(String sequencenumber) {
        this.sequencenumber = sequencenumber;
    }

    String sequencenumber;
    String userCreatedDate;
    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }


    public String getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(String userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }



//    public User(int imageResourceId, String userName, String userMobile, String userEmail) {
//        this.imageResourceId = imageResourceId;
//        this.userName = userName;
//        this.userMobile = userMobile;
//        this.userEmail = userEmail;
//        this.userCreatedDate = userCreatedDate;
//    }

}
