package com.example.thoughtchimp.com.example.thoughtchimp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thoughtchimp.dummyapplication.Milestone;
import com.example.thoughtchimp.dummyapplication.R;
import com.example.thoughtchimp.dummyapplication.ViewHolder;

import java.util.ArrayList;

/**
 * Created by thoughtchimp on 7/23/2016.
 */
public class Activationdapter  extends ArrayAdapter {


    public static final int Milestone1 = 0;
    public static final int Milestone2 = 1;
    public static final int Milestone3 = 2;
    int layoutResourceId;
    private Milestone[] data;
    Context ctx;

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return data[position].getType();
    }

    public Activationdapter(Context context, int layoutResourceId, Milestone[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.ctx = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Milestone listViewItem = data[position];
        int listViewItemType = getItemViewType(position);


        if (convertView == null) {

            if (listViewItemType == Milestone1) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.milestone1, null);
            } else if (listViewItemType == Milestone2) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.milestone2, null);
            } else  {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.milestone3, null);
            }

            TextView milestone = (TextView) convertView.findViewById(R.id.textView3);
            TextView activation = (TextView) convertView.findViewById(R.id.activation_text);
            TextView validfrom = (TextView) convertView.findViewById(R.id.valid_text);

            viewHolder = new ViewHolder(milestone,activation,validfrom);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.getText().setText(listViewItem.getText());



        return convertView;
    }

}
