package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoughtchimp on 7/27/2016.
 */
public class HomeFragment extends Fragment implements  Constant {
    TextView SessionText,ReccoText,SessionArcText,Username;
    ProgressBar progress;
    Button btn_score;
    private ArrayList<User> userlist=new ArrayList<>();
    final String url =CHILDHOMEIP;
    static String title1,sequenceid;
    public HomeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
//        protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


            SessionText= (TextView)rootView.findViewById(R.id.session_text);
            Username= (TextView)rootView.findViewById(R.id.username);
            Bundle extras = getArguments();
            final String names = extras.getString("chilprrofile");
            Username.setText(names);
            ReccoText=(TextView)rootView.findViewById(R.id.recco_text);
            SessionArcText= (TextView)rootView.findViewById(R.id.archieve_text);
            progress= (ProgressBar)rootView.findViewById(R.id.progressBar);
            btn_score=(Button)rootView.findViewById(R.id.button_score);
            final RequestQueue queue = Volley.newRequestQueue(getActivity());
            RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.rvAllUsers);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            final AllUsersAdapter allUserAdapter = new AllUsersAdapter(getActivity(), userlist);
            recyclerView.setAdapter(allUserAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        SessionArcText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),SessionArchive.class);
                startActivity(in);
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("------------------"+response);
                        try {
                            JSONObject jObj=new JSONObject(response);
                            int sesion=jObj.getInt("session_completed");
                            int Karmascore=jObj.getInt("freado");
                            int score=Karmascore/1000;
                            System.out.println("------------score"+score);
                            String Recco=jObj.getString("booster_bite");
                            String Session_archieve=jObj.getString("session_total");
                            JSONArray jsonArray=jObj.getJSONArray("sessions");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject id=jsonArray.getJSONObject(i);
                                User user=new User();
                                title1=id.getString("id");
                                sequenceid=id.getString("sequence");
                                //                String title2=title1.substring(7);
                                //                System.out.println("PPPPPPPPPPP"+title2);
                                //                String title3=title1;
                                //                System.out.println("____________"+title3);
                                user.setUserEmail(sequenceid);
                                user.setUserName(id.optString("title"));
                                user.setUserMobile(id.optString("parent_note"));
                                user.setImageResourceId(R.drawable.arrow_hdpi);
                                userlist.add(user);
                                allUserAdapter.notifyDataSetChanged();
                            }
                            ReccoText.setText(Recco);
                            progress.setMax(Integer.valueOf(Session_archieve));
                            progress.setProgress(sesion);
                            btn_score.setText(String.valueOf(score)+'k');
                            SessionText.setText(+sesion +" Miles Completed");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> httpget = new HashMap<>();
                httpget.put("X-API-KEY","123456");
                httpget.put("Authorization","Basic YWRtaW46MTIzNA==");
                httpget.put("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");
                //..add other headers
                return httpget;
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response.headers == null) {
                    // cant just set a new empty map because the member is final.
                    response = new NetworkResponse(
                            response.statusCode,
                            response.data,
                            Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                            response.notModified,
                            response.networkTimeMs);
                }

                return super.parseNetworkResponse(response);
            }
        };

        queue.add(stringRequest);
return rootView;
    }
}
