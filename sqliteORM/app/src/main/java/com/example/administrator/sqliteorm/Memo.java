package com.example.administrator.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Administrator on 2017-06-09.
 */

@DatabaseTable(tableName = "memo")
public class Memo {

    @DatabaseField(generatedId = true)   // autoIncrement
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String content;
    @DatabaseField
    private Date date;


//    @DatabaseField 이거 추가해 주지 않으면 컬럼으로 들어가지 않음
    String content2; //이거는 컬럼으로 들어가지 않음

    public Memo(){
        // OrmLite 는 기본 생성자가 없으면 동작하지 않는다.
        setDate();
    }

    public Memo(String title, String content){
        this.title = title;
        this.content = content;
        setDate();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public Date getDate() {
        return date;
    }

    // 아하!! 이렇게 setDate 를 불러올 때마다 새롭게 시간이 생성되도록 할 수 있구나
    // 일단은 메소드로 빼주는 것이 좋다. 이게 습관화가 되어야 하는군. 생성자에서 new Date 해 주는 것보다 이게 더 좋은 듯 하다
    private void setDate() {
        this.date = new Date(System.currentTimeMillis());
    }

}
