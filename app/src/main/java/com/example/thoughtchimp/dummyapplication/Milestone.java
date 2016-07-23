package com.example.thoughtchimp.dummyapplication;

/**
 * Created by thoughtchimp on 7/23/2016.
 */
public class Milestone {
    private String text;
    private int type;

    public Milestone(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}