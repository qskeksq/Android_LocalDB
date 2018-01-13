package com.example.administrator.sqliteorm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class BbsDao {

    DBHelper helper;
    Dao<Bbs, Integer> dao;

    public BbsDao(Context context){
        // 1. 데이터베이스에 연결
        helper = DBHelper.getInstance(context);
        try {
            dao = helper.getDao(Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Bbs bbs, Context context){
        // 2. 테이블 연결
        try {
            dao.create(bbs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bbs> read(){

        return null;
    }

    public void update(Bbs bbs){

    }

    public void delete(Bbs bbs){

    }

}
