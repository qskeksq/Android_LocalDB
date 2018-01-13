package com.example.administrator.sqliteorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;


public class DBHelper extends OrmLiteSqliteOpenHelper {

    // DbHeldper 를 메모리에 하나만 있게 해서 효율을 높혀보자 -- 바로 메모리에 하나 밖에 없는 싱글턴을 만들어보자

    private static DBHelper instance = null;
    public static DBHelper getInstance(Context context){
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }


    public static final String DATABASE_NAME = "database_name";
    public static final int DATABASE_VERSION = 2;

    // 아하! 이렇게 사용할 수 있구나. 다만 super는 반드시 채워 넣어줘야 하는군. 다만 인자로 받아와 쓸 필요가 없던 것이구나!!
    // 아하! 여태까지 이렇게 사용해 왔네 생각해보면.
    // 최초 호출될 때 database.db 파일을 /data/data/패키지명/database/디렉토리
    private DBHelper(Context context/* String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion*/) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 최초에 생성되면 버전체크를 해서 onCreate 를 호출한다.
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // 여기서 테이블을 생성한다.
        try {
            // 속성만 정의해 주면 된다.
            // *****************아하!!! onCreate 에는 기존의 이력이 있어야 한다***********************
            // 즉, 처음 하는 사람은 이 두 개를 모두, 이미 있는 사람은 업그레이드 버전을
            TableUtils.createTable(connectionSource, Memo.class);
            TableUtils.createTable(connectionSource, Bbs.class);  //어노테이션이 없으면 오류가 생김. 안 만들어도 되는 테이블이라는 뜻이기 때문.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 버전이 바뀌면 이미 설치한 사람은 onUpgrade 가, 앱이 없는 사람은 onUpgrade 가 호출된다.
    // 따라서 onCreate 에
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // Table 의 특정 컬럼만 병경될 경우
            // 1. 기존 테이블을 백업하기 위한 임시테이블을 생성하고 데이터를 담아둔다.
            //    예) create table temp_memo, <- 기존 데이터
            // 2. 메모 테이블을 삭제하고 다시 생성한다.
            // 3. 백업된 데이터를 다시 입력합니다
            // 4. 임시 테이블을 삭제합니다
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create -- 데이터 입력
    public void create(Memo memo){
        // 1. 테이블에 연결
        try {
            // 1. 테이블에 연결
            // 왜냐하면 테이블이 여러개일 수 있으니까 타입을 정해준 것이군
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object
            // 2. 데이터를 입력
            dao.create(memo);
        } catch (SQLException e) {

        }
        // 2. 데이터 입력
    }

    // Read
    public List<Memo> readAll(){
        List<Memo> datas = null;
        // 1. 테이블에 연결
        try {
            // 1. 테이블에 연결
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object

            // 방법1 -- 2.1 전체 데이터가 객체가 리스트에 담겨서 넘어온다.
            datas =  dao.queryForAll();

        } catch (SQLException e) {

        }

        return datas;
    }

    // search -- 데이터 검색하기
    public List<Memo> search(String words){
        List<Memo> datas = null;
        // 1. 테이블에 연결
        try {
            // 1. 테이블에 연결
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object

            // 2. 데이터 검색하기기
            String query = "select * from memo where content like '%"+words+"%'";
            GenericRawResults<Memo> temps = dao.queryRaw(query, dao.getRawRowMapper());
            datas = temps.getResults();

        } catch (SQLException e) {

        }

        return datas;
    }

    // Read
    public Memo readOne(int memoid){
        Memo memo = null;
        try {
            // 1. 테이블에 연결
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object

            // 방법1 -- 2.2 한 개의 데이터가 객체가 리스트에 담겨서 넘어온다.
            memo =  dao.queryForId(memoid);

        } catch (SQLException e) {

        }

        return memo;
    }

    // Update
    public void update(Memo memo){

        try {
            // 1. 테이블에 연결
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object

            // 2. 업데이트
            dao.update(memo);

        } catch (SQLException e) {

        }
    }

    // Delete -- 객체로 삭제하기
    public void delete(Memo memo){
        try {
            // 1. 테이블에 연결
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object

            // 2. 삭제
            dao.delete(memo);

        } catch (SQLException e) {

        }
    }

    // Delete -- 아이디로 삭제하기
    public void delete(int id){
        try {
            // 1. 테이블에 연결
            Dao<Memo, Integer> dao = this.getDao(Memo.class); // Data Access Object

            // 2. 삭제
            dao.deleteById(id);

        } catch (SQLException e) {

        }
    }


}
