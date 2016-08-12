package com.example.thoughtchimp.com.example.thoughtchimp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.thoughtchimp.freadom.ObjectDrawerItem;
import com.example.thoughtchimp.freadom.R;

import java.util.ArrayList;

/**
 * Created by admin on 21/12/15.
 */
public class AdapterDrawerList extends ArrayAdapter<ObjectDrawerItem>
{
    Context mContext;
    int layoutResourceId;
    ArrayList<ObjectDrawerItem> datas;

    public AdapterDrawerList(Context mContext, int layoutResourceId, ArrayList<ObjectDrawerItem> data)
    {
        super(mContext,layoutResourceId,  data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.datas = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imglistviewrowitem);
        TextView textViewName = (TextView) listItem.findViewById(R.id.txtlistviewrowitem);
        ObjectDrawerItem dItem = (ObjectDrawerItem) this.datas.get(position);
//        ObjectDrawerItem folder=new ObjectDrawerItem();
        imageViewIcon.setImageResource(dItem.icon);
        textViewName.setText(dItem.name);

        return listItem;
    }

}
