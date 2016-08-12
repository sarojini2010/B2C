package com.example.thoughtchimp.freadom;

import android.widget.TextView;

public class ViewHolder {
    TextView milestone;
    TextView activationcode;
    TextView validfrom;

    public TextView getChildname() {
        return childname;
    }

    public void setChildname(TextView childname) {
        this.childname = childname;
    }

    TextView childname;

    public TextView getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(TextView validfrom) {
        this.validfrom = validfrom;
    }

    public TextView getActivationcode() {
        return activationcode;
    }

    public void setActivationcode(TextView activationcode) {
        this.activationcode = activationcode;
    }



    public ViewHolder(TextView milestone,TextView validfrom,TextView activationcode) {
        this.milestone = milestone;
        this.validfrom=validfrom;
        this.activationcode=activationcode;
    }

    public TextView getText() {
        return milestone;
    }

    public void setText(TextView text) {
        this.milestone = text;
    }

}