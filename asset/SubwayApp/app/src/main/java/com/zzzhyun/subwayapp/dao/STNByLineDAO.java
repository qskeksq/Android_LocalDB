package com.zzzhyun.subwayapp.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.zzzhyun.subwayapp.module.STNBySubwayLine;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZHYUN on 2017-10-20.
 */
public class STNByLineDAO {
    DBHelper helper;
    Dao<STNBySubwayLine, Integer> dao = null;
    Context context;

    public STNByLineDAO(Context context){
        this.context = context;
        helper = DBHelper.getInstance(context);
        try {
            dao = helper.getDao(STNBySubwayLine.class);
            Log.e("STNByLineDAO이거 뭐지", dao.getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // R
    public List<STNBySubwayLine> searchList(String selectColumnName, String tbName, String whereColumnName, String searchVal, String orderBy){
        List<STNBySubwayLine> result = null;
        String query = DBHelper.searchList(selectColumnName, tbName, whereColumnName, searchVal, orderBy);
        Log.e("데이터 확인", query);

        TestDao testDao = new TestDao(context);
        Log.e("사이즈 확인", "사이즈가 몇이지"+testDao.getAll().size());
        try {
            Log.e("확인", "확인");
            Log.e("사이즈 확인", "사이즈가 몇이지"+dao.queryForAll().size());
            GenericRawResults<STNBySubwayLine> temp = dao.queryRaw(query, dao.getRawRowMapper());
            result = temp.getResults();
        } catch (SQLException e) {
            Log.e("read_DAO",e.toString());
        }
        Log.e("사이즈 확인", "사이즈가 몇이지"+result.size()+"");
        return result;
    }
}