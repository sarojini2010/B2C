package com.example.thoughtchimp.com.example.thoughtchimp.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thoughtchimp on 8/8/2016.
 */
public class ChildDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CHILDData.db";
    public static final String CONTACTS_TABLE_NAME = "childdata";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_name = "childname";


    public ChildDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table childdata "
                + "(id integer primary key, childid text, childname text, childgrade text, childimage text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS childdata");
        onCreate(db);

    }

    public boolean insertchilddata(String childid,String childname,String childgrade,String childimage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("childname", childname);
        contentValues.put("childid",childid);
        contentValues.put("childgrade",childgrade);
        contentValues.put("childimage",childimage);
        db.insert("childdata", null, contentValues);
        return true;
    }

    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from childdata where childname ='" + name + "'", null);
        return res;
    }

    public boolean updatechilddata(Integer id, String childname,String Childid,String childgrade,String childimage ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("childname", childname);
        contentValues.put("Childid", Childid);
        contentValues.put("childgrade", childgrade);
        contentValues.put("childimage", childimage);

        db.update("childdata", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deletechilddata(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("childdata",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

}