package com.example.thoughtchimp.dummyapplication;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 21/12/15.
 */
public interface Constant
{
    String BaseUrl1="http://192.168.0.100/s2m-b2c/Api/";
//   String BaseUrl1="http://www.connects2m.com/freadom/Api/";
    String CHILDHOMEIP=BaseUrl1+"child_home?child_id=9";
    String SessionDetailIp=BaseUrl1+"session_details?session_id=";
    String SessionArchieveIp=BaseUrl1+"archive?child_id=";
    String ACTIVATIONIP=BaseUrl1+"activation_details";
    String SESSIONDONE_URL=BaseUrl1+"session_done";
    String USER_SESSION_ID = "usersessionid";
    String SIGNUPIP=BaseUrl1+"signup";
    String PARENTIP=BaseUrl1+"update_parent_profile";
    String USER_NAME="name";
    String OTPsend=BaseUrl1+"send_otp?mobile=";
    String LOGINIP=BaseUrl1+"login";
    String CHILDADD=BaseUrl1+"create_child";
//    String BaseUrl="http://www.connects2m.com/freadom";
     String BaseUrl="http://192.168.0.100/s2m-b2c";
    String Updatechild=BaseUrl1+"update_child_profile";
    String accesstoken=LoginPage.access_token;
    String SessionUndo=BaseUrl1+"session_undo";
//    SharedPreferences activechildidprefernce = null;



}


