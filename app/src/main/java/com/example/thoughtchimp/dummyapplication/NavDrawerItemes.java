package com.example.thoughtchimp.dummyapplication;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItemes {
    private boolean showNotify;
    private String title;
    private int icon;






    public NavDrawerItemes() {

    }

    public NavDrawerItemes(int icon, String title) {
        this.icon = icon;
        this.title = title;
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
