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

/**
 * Клас для отправки сообщений на сервер
 */
public class WebUtils {

    /**
     * Метоод формирует POST запрос на указанный url с JSON объектом
     * @param url - адрес ресурса
     * @param jsonObject - JSON объект для отправки
     * @return ответный JSON, NULL-если ответа не было или возникла ошибка
     */
    public static JSONObject sendPost(String url, JSONObject jsonObject){
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            StringEntity params = new StringEntity(jsonObject.toString());
            HttpPost request = new HttpPost(url);
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity());
            if(!jsonString.isEmpty())
                return new JSONObject(jsonString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
