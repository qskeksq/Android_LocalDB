package com.zzzhyun.subwayapp.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.zzzhyun.subwayapp.module.StationInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZHYUN on 2017-10-20.
 */

public class StationInfoDAO {
    DBHelper helper;
    Dao<StationInfo, Long> dao = null;

    public StationInfoDAO(Context context){
        helper = DBHelper.getInstance(context);

        try {
            dao = helper.getDao(StationInfo.class);
            Log.e("StationInfoDAO", dao.getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // R
    public List<StationInfo> searchList(String selectColumnName, String tbName, String whereColumnName, String searchVal, String orderBy){
        List<StationInfo> result = null;
        String query = DBHelper.searchList(selectColumnName, tbName, whereColumnName, searchVal, orderBy);
        try {
            GenericRawResults<StationInfo> temp = dao.queryRaw(query, dao.getRawRowMapper());
            result = temp.getResults();
        } catch (SQLException e) {
            Log.e("read_DAO", e.toString());
        }
        return result;
    }
}