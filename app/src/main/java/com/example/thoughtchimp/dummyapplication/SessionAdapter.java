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
 * Created by thoughtchimp on 7/22/2016.
 */
public class SessionAdapter extends RecyclerView.Adapter {
    private Context mContext;
    ArrayList<Session> sessiondetailes;


    public SessionAdapter(Context mContext, ArrayList<Session> sessiondetailes) {
        this.mContext = mContext;
        this.sessiondetailes = sessiondetailes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.sesssioneach,null);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder holder1 = (UserViewHolder) holder;

        holder1.sesionid.setText(sessiondetailes.get(position).sessionid);
        holder1.seriesnumber.setText(sessiondetailes.get(position).sessionnumber);
        holder1.sessiondetails.setText(sessiondetailes.get(position).sessiondetails);
        holder1.iamge.setImageResource(sessiondetailes.get(position).imageResource);

        holder1.undo.setText(sessiondetailes.get(position).undosessionid);


    }

    @Override
    public int getItemCount() {
        return sessiondetailes.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView sesionid;
        private TextView seriesnumber;
        private TextView sessiondetails;
        private ImageView iamge;
        private TextView undo;

        public UserViewHolder(View itemView) {
            super(itemView);


            sesionid = (TextView) itemView.findViewById(R.id.sesion_id);
            seriesnumber = (TextView) itemView.findViewById(R.id.session_number);
            sessiondetails = (TextView) itemView.findViewById(R.id.session_description);
            iamge=(ImageView)itemView.findViewById(R.id.imageView);
            undo= (TextView) itemView.findViewById(R.id.undo);


        }


        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Session user =sessiondetailes.get(position);
            Intent in=new Intent(mContext,SessionDetails.class);
            in.putExtra("Sseeionid",sesionid.getText());
        }
    }
}
