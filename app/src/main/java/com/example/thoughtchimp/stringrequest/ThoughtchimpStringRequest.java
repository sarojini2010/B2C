package com.example.thoughtchimp.stringrequest;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.thoughtchimp.dummyapplication.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 21/12/15.
 */
public class ThoughtchimpStringRequest extends StringRequest implements Constant
{
    Context context;
    public ThoughtchimpStringRequest(Context context, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.context = context;
    }

    public ThoughtchimpStringRequest(Context context, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        this.context=context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headerMap = new HashMap<String, String>();
        Log.d("Context","context");
        headerMap.put("X-API-KEY","123456");
        headerMap.put("Authorization","Basic YWRtaW46MTIzNA==");
        headerMap.put("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");

        return headerMap;

    }



}
