package com.example.thoughtchimp.com.example.thoughtchimp.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thoughtchimp on 8/9/2016.
 */
public class ParentDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PARENTs.db";
    public static final String CONTACTS_TABLE_NAME = "parentdata";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_name = "parentname";


    public ParentDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table parentdata "
                + "(id integer primary key, parentid text, parentname text, parentemail text, parentmobilenumer text, accesstoken text,parentimages text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS parentdata");
        onCreate(db);

    }

    public boolean insertparentdata(String parentid,String parentname,String parentemail,String parentmobilenumer,String parentimages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("parentid", parentid);
        contentValues.put("parentname", parentname);
        contentValues.put("parentemail", parentemail);
        contentValues.put("parentmobilenumer", parentmobilenumer);
        contentValues.put("parentimages", parentimages);
        System.out.println("parentdatabase"+contentValues);
        db.insert("parentdata", null, contentValues);
        return true;
    }

    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from parentdata where parentname ='" + name + "'", null);
        return res;
    }

    public boolean updateparentdata(Integer id, String parentid,String parentname,String parentemail,String parentmobilenumer,String parentimages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("parentid", parentid);
        contentValues.put("parentname", parentname);
        contentValues.put("parentemail", parentemail);
        contentValues.put("parentmobilenumer", parentmobilenumer);
        contentValues.put("parentimages",parentimages);

        db.update("parentdata", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }


}