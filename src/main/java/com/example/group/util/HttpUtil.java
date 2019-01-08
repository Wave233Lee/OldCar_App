package com.example.group.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public static void sendPOSTRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null) {
                        Log.d("a_log","response: "+response.toString());
                        listener.OnFinish(response.toString());

                    }

                } catch (Exception e){
                    listener.onError(e);
                } finally {
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    public static void sendGETRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    Log.d("a_log",connection.getRequestMethod());
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null) {
                        Log.d("a_log","response: "+response.toString());
                        listener.OnFinish(response.toString());

                    }

                } catch (Exception e){
                    listener.onError(e);
                } finally {
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    public static void sendDELETERequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    Log.d("a_log",connection.getRequestMethod());
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    Log.d("a_log",connection.getRequestMethod());
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    Log.d("a_log",connection.getRequestMethod());
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null) {
                        Log.d("a_log","response: "+response.toString());
                        listener.OnFinish(response.toString());

                    }

                } catch (Exception e){
                    listener.onError(e);
                } finally {
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    //组装出带参数的完整URL
    public static String makeURL(String address,HashMap<String,String> params) throws UnsupportedEncodingException {
        //设置编码
        final String encode = "UTF-8";
        StringBuilder url = new StringBuilder(address);
        url.append("?");
        //将map中的key，value构造进入URL中
        for(Map.Entry<String, String> entry:params.entrySet())
        {
            url.append(entry.getKey()).append("=");
            url.append(URLEncoder.encode(entry.getValue(), encode));
            url.append("&");
            Log.d("a_log", ""+url);
        }
        //删掉最后一个&
        url.deleteCharAt(url.length() - 1);
        return url.toString();
    }
}
