package com.example.thoughtchimp.freadom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoughtchimp on 7/22/2016.
 */
public class SessionAdapter extends RecyclerView.Adapter implements Constant{
    private Context mContext;
    ArrayList<Session> sessiondetailes;
    String Url=SessionUndo;

    static  SharedPreferences activechildprefernce;

    String sessionid;
    String activechildid;

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

            activechildprefernce=mContext.getSharedPreferences("Activechild",mContext.MODE_PRIVATE);
            sesionid = (TextView) itemView.findViewById(R.id.sesion_id);
//            sessionarchiveid = (TextView) itemView.findViewById(R.id.sessionarchiveid);
            seriesnumber = (TextView) itemView.findViewById(R.id.session_number);
            sessiondetails = (TextView) itemView.findViewById(R.id.session_description);
            iamge=(ImageView)itemView.findViewById(R.id.imageView);
            undo= (TextView) itemView.findViewById(R.id.undo);
            sessionid=sesionid.getText().toString();
            activechildid=activechildprefernce.getString("activechild","");
            undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    undoarchive();
                    Intent in=new Intent(mContext,SessionArchive.class);
                    mContext.startActivity(in);
                }
            });


        }


        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Session user =sessiondetailes.get(position);
            Intent in=new Intent(mContext,SessionDetails.class);
            in.putExtra("Sseeionid",sesionid.getText());
        }
    }

    private void undoarchive() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Url);
        httpPost.addHeader("X-API-KEY","123456");
        httpPost.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
        httpPost.addHeader("access-token",accesstoken);



        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("session_id",sessionid));
        nameValuePair.add(new BasicNameValuePair("child_id",activechildid ));
//        nameValuePair.add(new BasicNameValuePair("device_token","fdsfgsdgfd"));


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder builder = new StringBuilder();
            String str = "";



            while ((str = rd.readLine()) != null) {
                builder.append(str);
            }

            String text = builder.toString();
            System.out.println();
            // write response to log
            Log.w("Http Post Response:", text.toString());

        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }


    }
}
