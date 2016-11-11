package com.example.quditcode_1.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by QuditCode-1 on 11/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "Student_Table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "FIRSTNAME";
    public static final String Col_3 = "LASTNAME";
    public static final String Col_4 = "MOBILE";



    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT,MOBILE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String firstName,String lastName,String mobile)
    {


        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,firstName);
        contentValues.put(Col_3,lastName);
        contentValues.put(Col_4,mobile);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;

        else
            return true;

    }


    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME,null);
        return  result;
    }

    public boolean updateData(String id,String firstName,String lastName,String mobile) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, firstName);
        contentValues.put(Col_3, lastName);
        contentValues.put(Col_4, mobile);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
