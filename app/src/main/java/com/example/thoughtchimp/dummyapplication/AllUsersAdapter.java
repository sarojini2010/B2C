package com.example.thoughtchimp.dummyapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder1.sessionid.setText(userList.get(position).sessionid);
        holder1.parentnote.setText(userList.get(position).parentnote);
        holder1.session_title.setText(userList.get(position).sessiontitle);
        holder1.sequencenumber.setText(userList.get(position).sequencenumber);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView session_title;
        private TextView sessionid;
        private TextView parentnote;
        private TextView sequencenumber;
        private ImageView iamge;

        public UserViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setClickable(true);

            session_title = (TextView) itemView.findViewById(R.id.session_title);
            sequencenumber = (TextView) itemView.findViewById(R.id.sesion_sequencenumber);
            parentnote = (TextView) itemView.findViewById(R.id.session_description);
            sessionid = (TextView) itemView.findViewById(R.id.session_id);
            iamge=(ImageView)itemView.findViewById(R.id.imageView);


        }

//        @Override
//        public void onClick(View v ,int pos) {
//            System.out.println("----------"+getItemId());
//            Intent in=new Intent(mContext,SessionDetails.class);
//            in.putExtra("Sessionid",HomeFragment.title1);
//            mContext.startActivity(in);
//        }

        @Override
        public void onClick(View v) {
            System.out.println("----------"+getItemId());
            int position = getLayoutPosition();
            User user = userList.get(position);
            Intent in=new Intent(mContext,SessionDetails.class);
//            Toast.makeText(mContext,"Sessionid"+ tvName.getText(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(mContext,ivProfile.getText(),Toast.LENGTH_LONG).show();
            in.putExtra("sessionid",sessionid.getText());
            in.putExtra("sessiontitle",session_title.getText());
            in.putExtra("sequencid",sequencenumber.getText());
            mContext.startActivity(in);
        }


    }
}