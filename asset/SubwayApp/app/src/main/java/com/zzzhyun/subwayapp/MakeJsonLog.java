package com.zzzhyun.subwayapp;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * 지하철 정보 제공 :
 * https://www.data.go.kr/dataset/15015807/openapi.do
 * https://www.data.go.kr/dataset/3045253/openapi.do
 *
 * Json To CSV 사이트 :
 * https://json-csv.com/
 */
public class MakeJsonLog extends MainActivity {

    public MakeJsonLog(List<String> listNowStation){
        for(int i=0; i<listNowStation.size(); i++){
            stationInfoRoad(listNowStation.get(i));
        }
    }
    // 노선별 지하철역 정보
    // http://data.seoul.go.kr/openinf/openapiview.jsp?infId=OA-12753&tMenu=11
    public void stationInfoRoad(final String stationName){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                return Remote.getData("http://swopenapi.seoul.go.kr/api/subway/"+getResources().getString(R.string.seoul_open_api_key)+"/json/stationInfo/0/200/"+stationName);
            }

            // 지하철 정보 출력 - log사용
            @Override
            protected void onPostExecute(String s) {
                Log.e("stationInfoRoad=======",s);
            }
        }.execute();
    }
}