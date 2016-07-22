package com.example.thoughtchimp.stringrequest;

import android.content.Context;
import android.provider.SyncStateContract;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.thoughtchimp.dummyapplication.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 7/12/15.
 */
public class ThoughtchimpJsonObjectRequest extends JsonObjectRequest implements Constant {

    Context context;
    Boolean isRequestDataJson;

    public ThoughtchimpJsonObjectRequest(Context context, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Boolean isRequestDataJson) {
        super(url, jsonRequest, listener, errorListener);
        this.context = context;
        this.isRequestDataJson = isRequestDataJson;
    }

    public ThoughtchimpJsonObjectRequest(Context context, int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Boolean isRequestDataJson) {
        super(method, url, jsonRequest, listener, errorListener);
        this.context = context;
        this.isRequestDataJson = isRequestDataJson;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        Map<String,String> map = new HashMap<>();
        map.put("Username","admin");
        map.put("Password","1234");
        return map;
    }
}
