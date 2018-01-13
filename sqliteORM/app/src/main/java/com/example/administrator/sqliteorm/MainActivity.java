package com.example.administrator.sqliteorm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = DBHelper.getInstance(getBaseContext());
        for(int i=0; i<10; i++) {
            Memo memo = new Memo();
            memo.setTitle("제목");
            memo.setContent("내용");
            helper.create(memo);
        }
//
//        // 2. 데이터 한 개 읽어오기
//        Memo memo = helper.readOne(3);
//        Log.i("Memo", memo.getId() + " title="+memo.getTitle()+" content="+memo.getContent());

//        // 3. 데이터 전체 읽어오기
//        List<Memo> datas = helper.readAll();
//        for( Memo memo : datas){
//            Log.i("Memo", memo.getId() + " title="+memo.getTitle()+" content="+memo.getContent());
//        }

        helper.create(new Memo("제목1", "내용1"));
        helper.create(new Memo("제목2", "내용2"));
        helper.create(new Memo("제목3", "내용3"));
        helper.create(new Memo("제목4", "내용4"));
        helper.create(new Memo("제목5", "내용5"));


        // 4. 데이터 검색하기
        List<Memo> datas = helper.search("내용3");
        for( Memo memo : datas){
            Log.i("Memo", memo.getId() + " title="+memo.getTitle()+" content="+memo.getContent());
        }

        // 5. 수정하기
        Memo memo = helper.readOne(3);
        memo.setContent("내용 바뀜");
        helper.update(memo);


        // 6. 삭제하기
        helper.delete(5);





    }
}
