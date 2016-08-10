package com.example.thoughtchimp.dummyapplication;

/**
 * Created by thoughtchimp on 8/10/2016.
 */
public class Childprofile {
    String childname;
    String profileimage;
    String childid;


    public String getChildid() {
        return childid;
    }

    public String setChildid(String childid) {
        this.childid = childid;
        return childid;
    }

    public String getChildname() {
        return childname;
    }

    public String setChildname(String childname) {
        this.childname = childname;
        return childname;
    }

    public String getProfileimage(String columnName) {
        return profileimage;
    }

    public String setProfileimage(String profileimage) {
        this.profileimage = profileimage;
        return profileimage;
    }


}
