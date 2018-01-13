package com.example.administrator.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.realm.realm.Bbs;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void create(){
        /* 동기로 처리 */
        // 1. 인스턴스 생성 - connection
        Realm realm = Realm.getDefaultInstance();
        // 2. 트랜젝션 시작
        realm.beginTransaction();
        // 테이블 처리
        Number maxValue = realm.where(Bbs.class).max("no");
        int no = (maxValue != null ? maxValue.intValue() +1 : 1);
        Bbs bbs = realm.createObject(Bbs.class, no); // 레코드 한 개 생성
        bbs.setNo(1);
        bbs.setTitle("제목1");
        bbs.setContent("내용을 여기");
        bbs.setDate(System.currentTimeMillis());
        // 테이블에 한 개의 레코드셋이 들어간다
        realm.commitTransaction();

        /* 비비동기로 처리 */
        RealmAsyncTask task = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm asyncRealm) {
                Bbs bbs = asyncRealm.createObject(Bbs.class);
                bbs.setNo(1);
                bbs.setTitle("제목1");
                bbs.setContent("내용을 여기");
                bbs.setDate(System.currentTimeMillis());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // 끝나고 호출될 함수
                afterCreation();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // 오류 함수
            }
        });
    }

    public void afterCreation(){
        Toast.makeText(this, "데이터가 입력되었습니다", Toast.LENGTH_SHORT).show();
    }

    public void read(){
        Realm realm = Realm.getDefaultInstance();
        // select * from bbs where no=1 or title="제목1";
        RealmQuery<Bbs> query = realm.where(Bbs.class);
        query.equalTo("no", 1);
        query.or().equalTo("title", "제목1");
        /* 동기로 질의 */
        RealmResults<Bbs> result = query.findAll();
        /* 비동기로 질의 */
        RealmResults<Bbs> result2 = query.findAllAsync();
        result2.addChangeListener(new RealmChangeListener<RealmResults<Bbs>>() {
            @Override
            public void onChange(RealmResults<Bbs> bbses) {

            }
        });

        Log.d("Result", "쿼리결과");
    }

    public void update(){
//        Realm realm = Realm.getDefaultInstance();
//        // select * from bbs where no=1 or title="제목1";
//        RealmQuery<Bbs> query = realm.where(Bbs.class);
//        /* 동기로 질의 */
//        query.findAllAsync().addChangeListener(new RealmChangeListener<RealmResults<Bbs>>() {
//            @Override
//            public void onChange(RealmResults<Bbs> bbses) {
//                Bbs bbs1 = bbses.first();
//            }
//        });

        // 1. 수정할 객체를 가져오거나 생성
        Bbs bbs = new Bbs();
        bbs.setNo(1);
        bbs.setTitle("제목");
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 있으면 업데이트 없으면 생성성
               realm.copyToRealmOrUpdate(bbs);
            }
        });
    }

    public void delete(){

    }

}
