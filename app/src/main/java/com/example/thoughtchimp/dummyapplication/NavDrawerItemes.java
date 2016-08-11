package com.example.thoughtchimp.dummyapplication;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItemes {
    private boolean showNotify;
    private String title;
    int icons;

    public int getIcons() {
        return icons;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String image;
    String id;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    String method;

    public int getViewnumber() {
        return viewnumber;
    }

    public void setViewnumber(int viewnumber) {
        this.viewnumber = viewnumber;
    }

    int viewnumber;



    public NavDrawerItemes() {

    }

    public NavDrawerItemes(int icons, String title,String id,int viewnumber,String method,String images) {

        this.title = title;
        this.id=id;
        this.image=images;
        this.icons=icons;
        this.viewnumber=viewnumber;
        this.method=method;
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


}
