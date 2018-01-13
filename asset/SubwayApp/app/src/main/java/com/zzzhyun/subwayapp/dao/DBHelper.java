package com.zzzhyun.subwayapp.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ZHYUN on 2017-09-22.
 */

// orm을 사용하기위해 sqliteOpenHelper를 상속받지 않고 OrmliteOpenHelper를 상속받는다.
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG     = "DBHelper";
    private static final String DB_NAME = "zhyunStation.db";
    private static final int DB_VERSION = 1;
    private static DBHelper DBHelper    = null;
    public Context mContext;
    final String DB_PATH;


    private DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
        DB_PATH = "/data/data/"+context.getPackageName()+"/"+"databases/";

        Log.e(TAG,"Constructor========");
    }

    public static DBHelper getInstance(Context context){
        if(DBHelper == null)  DBHelper = new DBHelper(context);
        return DBHelper;
    }

    /**
     * 외부에서 데이터베이스를 불러올 때 SQLiteORM 사용하는 방법
     * 1. 언제 저장하든 상관 없이 데이터베이스 저장 위치에 db 파일을 복사해 넣어준다
     * 2. pojo 객체에 어노테이션과 반드시 일반 생성자를 만든다.
     * @param database
     * @param connectionSource
     */


    /**
     * ormlite에서 onCreate는 데이터베이스에 스트림을 연결해서 테이블을 만드는 일까지 한다.
     * 따라서 이곳에서 데이터베이스에 또 outputstream을 열어버리면 copy가 될 수 없다.
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.e(TAG,"onCreate========S");

        if(database.isOpen()){
            database.close();
        }

        TestDao dao = new TestDao(mContext);
        dao.getAll();

//        File file = mContext.getDatabasePath(DB_NAME);
//        Log.e("file.length =======",file.length()+"");
//        try {
//            copyDB(mContext);
//        TestDao testDao = new TestDao(mContext);
//        testDao.getAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            TableUtils.createTable(connectionSource, new DatabaseTableConfig<Object>());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            TableUtils.createTableIfNotExists(connectionSource, STNBySubwayLine.class);
//            TableUtils.createTableIfNotExists(connectionSource, StationInfo.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if(!file.exists()){

//            onCreate(database, connectionSource);
//        }

        Log.e(TAG,"onCreate========E");
    }



    // DB를 복사하기
    // assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기
    public void copyDB(Context mContext){
        Log.e("MiniApp", "copyDB");
        AssetManager manager = mContext.getAssets();
//        File file = mContext.getDatabasePath(DB_NAME);

//        Log.e("경로 확인", file.toString());
        //  /data/user/0/com.zzzhyun.subwayapp/databases/zhyunStation.db
        //  /data/data/com.zzzhyun.subwayapp/databases/zhyunStation.db
//        FileOutputStream fos = null;
//        BufferedOutputStream bos = null;
//        try {
//            Log.e("통과 확인", "통과 확인=======");
//            InputStream is = manager.open("db/" + DB_NAME);
//            BufferedInputStream bis = new BufferedInputStream(is);
//
//
//
//            fos = new FileOutputStream(DB_PATH+DB_NAME);
//            bos = new BufferedOutputStream(fos);
//            int read = -1;
//            byte[] buffer = new byte[1024];
//            while ((read = bis.read(buffer, 0, 1024)) != -1) {
//                bos.write(buffer, 0, read);
//            }
//
//            bos.flush();
//
//            bos.close();
//            fos.close();
//            bis.close();
//            is.close();
//            Log.e("통과 확인", "통과 확인=======");
//        } catch (IOException e) {
//            Log.e("ErrorMessage : ", e.getMessage());
//        }
        try {
            InputStream myInput = mContext.getAssets().open("db/" + DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void copyDatabase() throws IOException {
        InputStream myInput = mContext.getAssets().open("db/"+DB_NAME);
        String outFileName = DB_PATH+DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.e(TAG,oldVersion+"==="+newVersion);
    }

    public static String searchList(String selectColumnName, String tbName, String whereColumnName, String searchVal, String orderBy){
        String query = "";
        query += String.format("SELECT DISTINCT %s FROM %s", selectColumnName, tbName);
        if(whereColumnName != null && searchVal != null )
            query += String.format(" WHERE %s = '%s'", whereColumnName, searchVal);
        if(orderBy != null)
            query += String.format(" ORDER BY %s", orderBy);

        Log.e("result query -- ",query);
        return query;
    }
}