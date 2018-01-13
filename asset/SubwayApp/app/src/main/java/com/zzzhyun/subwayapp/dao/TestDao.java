package com.zzzhyun.subwayapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zzzhyun.subwayapp.module.STNBySubwayLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-10-24.
 */
public class TestDao {

    SQLiteDatabase db;
    TestHelper helper;

    public TestDao(Context context) {

        helper = new TestHelper(context);

        try {
            helper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        helper.openDatabase();

        db = helper.getDb();

    }

    public List<STNBySubwayLine> getAll(){

        List<STNBySubwayLine> list = new ArrayList<>();
        String[] col = {"STATION_NM"};
        String whereClause = "LINE_NUM = ?";
        String[] whereArgs = { "1" };
        Cursor cursor = db.query("TB_STN_BY_SUBWAY_LINE", null, whereClause, whereArgs, null, null, null);
        while(cursor.moveToNext()){
            STNBySubwayLine subwayLine = new STNBySubwayLine();
            subwayLine.setSTATION_CD(cursor.getString(cursor.getColumnIndex("STATION_CD")));
            subwayLine.setSTATION_NM(cursor.getString(cursor.getColumnIndex("STATION_NM")));
            subwayLine.setLINE_NUM(cursor.getString(cursor.getColumnIndex("LINE_NUM")));
            subwayLine.setFR_CODE(cursor.getString(cursor.getColumnIndex("FR_CODE")));
            list.add(subwayLine);
        }
        return list;
    }

}
