package com.zzzhyun.subwayapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.zzzhyun.subwayapp.dao.STNByLineDAO;
import com.zzzhyun.subwayapp.dao.StationInfoDAO;
import com.zzzhyun.subwayapp.module.RealtimeArrivalList;
import com.zzzhyun.subwayapp.module.RealtimeSubway;
import com.zzzhyun.subwayapp.module.STNBySubwayLine;
import com.zzzhyun.subwayapp.module.StationInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 지하철 정보 :
 * https://www.data.go.kr/dataset/15015807/openapi.do
 * https://www.data.go.kr/dataset/3045253/openapi.do
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public STNByLineDAO STNByLineDAO;
    public StationInfoDAO StationInfoDAO;

    // 위젯
    ConstraintLayout container;
    RecyclerView rv;
    TextView tvNow, tvPrev, tvNext, tvBg, tvPrevArrow, tvNextArrow;
    Spinner stationSpinner, lineSpinner;

    // 아답터
    ArrayAdapter stationAdapter;

    // 데이터
    List<RealtimeArrivalList[]> dataPair = new ArrayList<>();   // 가공된 실시간 열차 정보 목록
    List<RealtimeArrivalList> data = new ArrayList<>(); // 실시간 열차 정보 목록
    List<String> listNowStation = new ArrayList<>();    //
    List<String> listLine;      // 1호선, 인천1호선 ... List
    List<String> listLineKey;   // 1, S .. List
    String statnId = "";        // 지하철역 id [호선+역]
    String nowLine = "";        // 1호선, 인천1호선 ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        StationInfoDAO = new StationInfoDAO(this);
        STNByLineDAO = new STNByLineDAO(this);

        initView();
         STNByLineDAO.searchList("STATION_NM", "TB_STN_BY_SUBWAY_LINE", "LINE_NUM", "1", "FR_CODE ASC");
        initSpinnerLine();
    }

    // 위젯 연결
    public void initView(){
        container = (ConstraintLayout) findViewById(R.id.container);
        rv = (RecyclerView) findViewById(R.id.rv);
        tvNow = (TextView) findViewById(R.id.tvNow);
        tvPrevArrow = (TextView) findViewById(R.id.tvPrevArrow);
        tvNextArrow = (TextView) findViewById(R.id.tvNextArrow);
        tvPrev = (TextView) findViewById(R.id.tvPrev);
        tvNext = (TextView) findViewById(R.id.tvNext);
        tvBg = (TextView) findViewById(R.id.tvBg);
        stationSpinner = (Spinner) findViewById(R.id.stationSpinner);
        lineSpinner = (Spinner) findViewById(R.id.lineSpinner);

        listLine = Arrays.asList(getResources().getStringArray(R.array.lineVal));
        listLineKey = Arrays.asList(getResources().getStringArray(R.array.lineKey));

    }

    // Line 스피너 연결
    public void initSpinnerLine(){
        ArrayAdapter lineAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listLine);
        lineAdapter.setDropDownViewResource(R.layout.sinner_dropdown_item);

        lineSpinner.setAdapter(lineAdapter);
        lineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stationLineRoad(listLineKey.get(position));
                nowLine = listLine.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // 역명 스피너 연결
    public void initSpinnerStation(){
        stationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listNowStation);
        stationAdapter.setDropDownViewResource(R.layout.sinner_dropdown_item);
        stationSpinner.setAdapter(stationAdapter);

        stationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stationInfoRoad(listNowStation.get(position), nowLine);
                tvNow.setText(listNowStation.get(position));
                if(position-1 > -1) {
                    tvPrevArrow.setText("◀");
                    tvPrev.setText(listNowStation.get(position - 1));
                } else {
                    tvPrevArrow.setText("　");
                    tvPrev.setText("　　　");
                }

                if(listNowStation.size() > position+1) {
                    tvNextArrow.setText("▶");
                    tvNext.setText(listNowStation.get(position + 1));
                } else {
                    tvNextArrow.setText("　");
                    tvNext.setText("　　　");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // 역 정보 저장하기 위한 저장소 --> DB로 옮김
    public void stationLineRoad(final String line){
        new AsyncTask<Void, Void, List<STNBySubwayLine>>(){
            @Override
            protected List<STNBySubwayLine> doInBackground(Void... voids) {
                return STNByLineDAO.searchList("STATION_NM", "TB_STN_BY_SUBWAY_LINE", "LINE_NUM", line, "FR_CODE ASC");
            }

            @Override
            protected void onPostExecute(List<STNBySubwayLine> s) {
                listNowStation.clear();
                for(int i=0; i<s.size(); i++){
                    listNowStation.add(s.get(i).getSTATION_NM());
                }
                initSpinnerStation();
            }
        }.execute();
    }

    // 노선별 지하철역 정보 -> DB로 옮김
    // http://data.seoul.go.kr/openinf/openapiview.jsp?infId=OA-12753&tMenu=11
    public void stationInfoRoad(final String stationName, final String lineName){
        new AsyncTask<Void, Void, List<StationInfo>>(){
            @Override
            protected List<StationInfo> doInBackground(Void... voids) {
                return StationInfoDAO.searchList("statnId, statnNm, subwayNm", "TB_STATION_INFO", "statnNm", stationName, null);
            }

            @Override
            protected void onPostExecute(List<StationInfo> s) {
                Log.e("lineName",lineName);
                List<StationInfo> temp = s;
                for(int i=0; i<temp.size(); i++){
                    if(temp.get(i).getSubwayNm().equals(lineName)) {
                        statnId = temp.get(i).getStatnId();
                        realTimeRoad(temp.get(i).getStatnNm());
                        break;
                    }
                }
            }
        }.execute();
    }

    // 실시간 운행정보 저장하기 위한 저장소 // subwayId으로 값 찾기[호선 , 역]
    public void realTimeRoad(final String stationName){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                return Remote.getData("http://swopenapi.seoul.go.kr/api/subway/"+getResources().getString(R.string.seoul_open_api_key)+"/json/realtimeStationArrival/0/200/"+Uri.encode(stationName));
            }

            @Override
            protected void onPostExecute(String s) {
                Log.e("realTimeRoad",s);
                data = new ArrayList<>();
                Gson gson = new Gson();
                RealtimeSubway realtimeSubway = gson.fromJson(s, RealtimeSubway.class);
                if(realtimeSubway.getRealtimeArrivalList() != null) {
                    // 여기에서 statnId 가지고 값 필터 해야됨 [호선별 출력 필요] // 같지 않은 data 값 제거
                    RealtimeArrivalList[] temp = realtimeSubway.getRealtimeArrivalList();
                    for (RealtimeArrivalList ral : temp) {
                        if (ral.getStatnId().equals(statnId))
                            data.add(ral);
                    }
                } else {
                    data = null;
                }
                setList();
            }
        }.execute();
    }

    public void setList(){
        // 여기까지 data는 상하행모두 출력된 list
        dataPair.clear();

        if(data == null){
            dataPair.add(null);
        } else {
            // 이후 data는 상하행으로 이루어진 크기2의 배열이 담긴 list
            RealtimeArrivalList[] temp;
            String updnLine;
            while (data.size() > 0) {
                temp = new RealtimeArrivalList[2];
                for (int i = 0; i < data.size(); i++) {
                    updnLine = data.get(i).getUpdnLine();
                    if (updnLine.equals("상행")) {
                        temp[0] = data.get(i);
                        data.remove(i);
                        break;
                    }
                }
                for (int i = 0; i < data.size(); i++) {
                    updnLine = data.get(i).getUpdnLine();
                    if (updnLine.equals("하행")) {
                        temp[1] = data.get(i);
                        data.remove(i);
                        break;
                    }
                }
                dataPair.add(temp);
            }
        }
        ListAdapter adapter = new ListAdapter(dataPair, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
//        tvNext.setOnClickListener(); : 작성해야됨
    }
}