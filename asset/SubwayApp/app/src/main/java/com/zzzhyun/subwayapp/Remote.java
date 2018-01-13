package com.zzzhyun.subwayapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * URI 데이터 가져오기
 * Created by ZHYUN on 2017-10-16.
 */

public class Remote {

    public static String getData(String uri) {
        String TAG = "Thread";
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(inputStreamReader);
                String temp="";
                while ((temp = br.readLine()) != null) {
                    result.append(temp).append("\n");
                }
                br.close();
                inputStreamReader.close();
            } else {
                Log.e(TAG, "========================"+con.getResponseCode());
            }
            con.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result.toString();
    }

}
