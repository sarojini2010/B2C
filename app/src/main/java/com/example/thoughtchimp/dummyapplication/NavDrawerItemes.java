package com.example.thoughtchimp.dummyapplication;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItemes {
    private boolean showNotify;
    private String title;
    private int icon;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NavDrawerItemes() {

    }

    public NavDrawerItemes(int icon, String title,String id) {
        this.icon = icon;
        this.title = title;
        this.id=id;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
