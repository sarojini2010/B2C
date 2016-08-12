package com.example.thoughtchimp.freadom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ChildDatabase;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ParentDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoughtchimp on 7/27/2016.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements  Constant {
    TextView SessionText,ReccoText,SessionArcText,Username;
    ProgressBar progress;
    Button btn_score;
    private ArrayList<User> userlist=new ArrayList<>();
    final String url =BaseUrl;
    String Urls;
    static String sessionid,sequenceid,childimages;
    ImageView childprofile;
    Bitmap b;
    String child_id,childid;
    ImageView child_image;
    ChildDatabase childDatabase;
    ParentDatabase parentDatabase;
    URL newurl;
    static String childimage;
    SharedPreferences sharedPreferences2;
    public HomeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        //..add other headers

//        protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

            sharedPreferences2 = getActivity().getSharedPreferences("ChildProfile3", 1);
            String id=sharedPreferences2.getString("chilid","");
        childDatabase=new ChildDatabase(getActivity());
        parentDatabase=new ParentDatabase(getActivity());
        System.out.println("childdatabasedetails"+childDatabase.getDatabaseName());
        System.out.println("childdatabasedetails"+parentDatabase.getDatabaseName());
        child_image= (ImageView) rootView.findViewById(R.id.childimages22);
//        System.out.println("childdatabase"+childDatabase.getData("102"));
        Bundle bnd=getArguments();
        child_id=bnd.getString("childid");
        childimage=bnd.getString("childimages");
        String childname=bnd.getString("childname");

        try {
            newurl = new URL(BaseUrl+"//uploads/profile/child/"+childimage);
            BitmapFactory.Options options = new BitmapFactory.Options();
            b = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            child_image.setImageBitmap(b);
            child_image.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        child_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),Profile.class);
                in.putExtra("profile",Uri.parse(url));
                startActivity(in);
            }
        });

//        Cursor childetails = childDatabase.getData(LoginPage.firstchild);
//
//        System.out.println("===========childidetils"+childetails);
//        if (childetails.moveToFirst()) {
//
//            childid = childetails.getString(1);
//            System.out.println("---child"+childid);
//        }
            childid=child_id;

            Urls=BaseUrl1+"child_home?child_id="+childid;
        System.out.println("childurl"+Urls);
            SessionText= (TextView)rootView.findViewById(R.id.session_text);
            Username= (TextView)rootView.findViewById(R.id.username);

            Bundle extras = getArguments();
            final String names = extras.getString("chilprrofile");
            Username.setText(childname);

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
//                childimages=sharedPreferences2.getString("childimages","");
//        URL newurls;
//        try {
//            newurls = new URL(BaseUrl + "//uploads/profile/child/" + childimages);
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            b= BitmapFactory.decodeStream(newurls.openConnection().getInputStream());
//            childprofile.setImageBitmap(b);
//            childprofile.setScaleType(ImageView.ScaleType.FIT_XY);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        SessionArcText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),SessionArchive.class);
                startActivity(in);
            }
        });
//        childprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in =new Intent(getActivity(),Profile.class);
//                startActivity(in);
//            }
//        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls,
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
//                                sessionid=id.getString("id");
//                                sequenceid=id.getString("sequence");
                                //                String title2=title1.substring(7);
                                //                System.out.println("PPPPPPPPPPP"+title2);
                                //                String title3=title1;
                                //                System.out.println("____________"+title3);

                                user.setSessionid(id.optString("id"));
                                user.setSessiontitle(id.optString("title"));
                                user.setParentnote(id.optString("parent_note"));
                                user.setImageResourceId(R.drawable.arrow_hdpi);
                                user.setSequencenumber(id.optString("sequence"));
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
                httpget.put("access-token",accesstoken);
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
