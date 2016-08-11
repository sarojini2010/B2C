//package com.example.thoughtchimp.com.example.thoughtchimp.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.thoughtchimp.dummyapplication.Childdetails;
//import com.example.thoughtchimp.dummyapplication.HomeFragment;
//import com.example.thoughtchimp.dummyapplication.MainActivity;
//import com.example.thoughtchimp.dummyapplication.R;
//import com.example.thoughtchimp.dummyapplication.User;
//
//import java.util.ArrayList;
//
///**
// * Created by thoughtchimp on 8/4/2016.
// */
//public class Listadapter extends BaseAdapter {
//    ArrayList<Childdetails> child;
//    int[] imageId;
//    Activity context;
//    private static LayoutInflater inflater = null;
//
//    public Listadapter(Activity context, ArrayList<Childdetails> childetils) {
//        // TODO Auto-generated constructor stub
//        this.child = childetils;
//        context = context;
//        inflater = ( LayoutInflater )context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return child.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    public class Holder {
//        TextView tv;
//        ImageView img;
//    }
//
//    @Override
//    public View getView(final int position, final View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        final Holder holder = new Holder();
//        View rowView;
//        rowView = inflater.inflate(R.layout.navigationmaterial, null);
//        holder.tv = (TextView) rowView.findViewById(R.id.textView1);
//        holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
//        holder.tv.setText(child.get(position).name[position]);
//        holder.img.setImageResource(child.get(position).profilepic[position]);
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              Intent in=new Intent(context,HomeFragment.class);
//                context.startActivity(in);
//            }
//        });
//
//        return rowView;
//    }
//}