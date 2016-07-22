package com.example.thoughtchimp.stringrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.thoughtchimp.dummyapplication.Constant;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ThoughtchimpMultipartRequest extends Request<NetworkResponse> implements Constant {
    private final Response.Listener<NetworkResponse> mListener;
    private final Response.ErrorListener mErrorListener;
    private final String mMimeType;
    private final byte[] mMultipartBody;
    private Context context;


    public static ThoughtchimpMultipartRequest getInstance(Context context, String url, String mimeType, File file, Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener) throws IOException {
        byte[] multipartBody = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(multipartBody);
        Log.d("UPLOAD", "File size" + multipartBody.length);
        return new ThoughtchimpMultipartRequest(context,url,mimeType,multipartBody,listener,errorListener);
    }

    public ThoughtchimpMultipartRequest(Context context, String url, String mimeType, byte[] multipartBody, Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        this.mErrorListener = errorListener;
        this.mMimeType = mimeType;
        this.context = context;
        this.mMultipartBody = multipartBody;

    }

    @Override
    public String getBodyContentType() {
        return mMimeType;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return mMultipartBody;
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(
                    response,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> map = new HashMap<>();
        map.put("Username","admin");
        map.put("Password","1234");
        return map;
    }
}