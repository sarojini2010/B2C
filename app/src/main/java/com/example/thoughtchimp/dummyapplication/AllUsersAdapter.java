package com.example.thoughtchimp.dummyapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thoughtchimp on 7/14/2016.
 */
public  class  AllUsersAdapter extends RecyclerView.Adapter {
    private Context mContext;
    ArrayList<User> userList;

    public AllUsersAdapter(Context mContext, ArrayList<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.singleuser,null);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder holder1 = (UserViewHolder) holder;
        holder1.iamge.setImageResource(userList.get(position).imageResourceId);
        holder1.tvName.setText(userList.get(position).userName);
        holder1.tvMobile.setText(userList.get(position).userMobile);
        holder1.ivProfile.setText(userList.get(position).userEmail);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView ivProfile;
        private TextView tvName;
        private TextView tvMobile;
        private ImageView iamge;

        public UserViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setClickable(true);

            ivProfile = (TextView) itemView.findViewById(R.id.sesion_id);
            tvName = (TextView) itemView.findViewById(R.id.session_number);
            tvMobile = (TextView) itemView.findViewById(R.id.session_description);
            iamge=(ImageView)itemView.findViewById(R.id.imageView);


        }

        @Override
        public void onClick(View v) {
            System.out.println("----------"+getItemId());
            Intent in=new Intent(mContext,SessionDetails.class);
            in.putExtra("Sessionid",HomeFragment.title1);
            mContext.startActivity(in);
        }
    }
}