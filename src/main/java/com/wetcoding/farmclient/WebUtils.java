package com.wetcoding.farmclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WebUtils {
    public String sendGet(String url,String par){
        return new String();
    }

    public JSONObject sendPost(String url, JSONObject jsonObject){
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            StringEntity params = new StringEntity(jsonObject.toString());
            HttpPost request = new HttpPost(url);
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity());
            return new JSONObject(jsonString);
        } catch (Exception e) {
        } finally {
            //httpClient.??
        }

        return null;//??
    }
}
