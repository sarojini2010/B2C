package com.example.thoughtchimp.dummyapplication;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by thoughtchimp on 7/20/2016.
 */
public class JsonParserclass {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String body = "";

    // constructor
    public JsonParserclass() {

    }

    public JSONObject getJSONFromUrl(String url) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            httpget.addHeader("X-API-KEY", "123456");
            httpget.addHeader("Authorization", "Basic YWRtaW46MTIzNA==");
            httpget.addHeader("access-token", "6InFDMC1mYyvJ0QoxiL8dEUSj_2");

            HttpResponse httpResponse = httpClient.execute(httpget);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);

            }
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            body = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(body);
            System.out.println("---------------------------" + jObj);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }
}
