//package com.example.thoughtchimp.dummyapplication;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by thoughtchimp on 7/22/2016.
// */
//public class SessionAdapter extends RecyclerView.Adapter {
//    private Context mContext;
//    ArrayList<Session> sessiondetails;
//
//    public SessionAdapter(Context mContext, ArrayList<Session> sessiondetails) {
//        this.mContext = mContext;
//        this.sessiondetails = sessiondetails;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(mContext).inflate(R.layout.sesssioneach,null);
//        UserViewHolder viewHolder = new UserViewHolder(view);
//        return viewHolder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        UserViewHolder holder1 = (UserViewHolder) holder;
//
//        holder1.sesionid.setText(sessiondetails.get(position).);
//        holder1.seriesnumber.setText(sessiondetails.get(position).);
//        holder1.sessiondetails.setText(sessiondetails.get(position).sessiondetails);
//        holder1.iamge.setImageResource(sessiondetails.get(position).imageResource);
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return sessiondetails.size();
//    }
//
//
//
//    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        private TextView sesionid;
//        private TextView seriesnumber;
//        private TextView sessiondetails;
//        private ImageView iamge;
//
//        public UserViewHolder(View itemView) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            itemView.setClickable(true);
//
//            sesionid = (TextView) itemView.findViewById(R.id.sesion_id);
//            seriesnumber = (TextView) itemView.findViewById(R.id.session_number);
//            sessiondetails = (TextView) itemView.findViewById(R.id.session_description);
//            iamge=(ImageView)itemView.findViewById(R.id.imageView);
//
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            Intent in=new Intent(mContext,SessionArchieve.class);
//            mContext.startActivity(in);
//        }
//    }
//}
