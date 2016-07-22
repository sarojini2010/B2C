package com.example.thoughtchimp.dummyapplication;

/**
 * Created by thoughtchimp on 7/14/2016.
 */
public class User {
    int imageResourceId;
    String userName;
    String userMobile;
    String userEmail;
    String userCreatedDate;
    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
