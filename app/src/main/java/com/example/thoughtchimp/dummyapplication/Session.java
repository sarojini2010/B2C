package com.example.thoughtchimp.dummyapplication;

/**
 * Created by thoughtchimp on 7/22/2016.
 */
public class Session {
    String sessionid;
    String sessionnumber;
    String sessiondetails;
    String undosessionid;

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    int imageResource;


    public String getUndosessionid() {
        return undosessionid;
    }

    public void setUndosessionid(String undosessionid) {
        this.undosessionid = undosessionid;
    }


    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSessionnumber() {
        return sessionnumber;
    }

    public void setSessionnumber(String sessionnumber) {
        this.sessionnumber = sessionnumber;
    }

    public String getSessiondetails() {
        return sessiondetails;
    }

    public void setSessiondetails(String sessiondetails) {
        this.sessiondetails = sessiondetails;
    }


}
