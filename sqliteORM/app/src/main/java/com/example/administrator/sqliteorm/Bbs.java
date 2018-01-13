package com.example.administrator.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bbs")
public class Bbs {

    @DatabaseField(generatedId = true)
    private int id;

}
