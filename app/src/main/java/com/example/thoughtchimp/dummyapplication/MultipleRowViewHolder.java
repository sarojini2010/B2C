package com.example.thoughtchimp.dummyapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



/**
 * Created by nitesh on 10/14/2015.
 */
public class MultipleRowViewHolder extends RecyclerView.ViewHolder {

    public TextView activationcode;
    public TextView valid,childname;

    private int type;

    public MultipleRowViewHolder(View itemView, int type) {
        super(itemView);

        if (type == Actionconstant.Milestone1){
            activationcode = (TextView)itemView.findViewById(R.id.activation_text);
            valid = (TextView)itemView.findViewById(R.id.valid_text);
//            childname=(TextView)itemView.findViewById(R.id.childname);

        }else if(type == Actionconstant.Milestone2) {
            activationcode = (TextView)itemView.findViewById(R.id.activation_text1);
            valid = (TextView)itemView.findViewById(R.id.valid_text1);
//            childname=(TextView)itemView.findViewById(R.id.childname1);
        }else if(type ==  Actionconstant.Milestone3) {
            activationcode = (TextView)itemView.findViewById(R.id.activation_text3);
            valid = (TextView)itemView.findViewById(R.id.valid_text3);
//            childname=(TextView)itemView.findViewById(R.id.childname3);
        }
    }
}
