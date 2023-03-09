package com.example.currencycunverter;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //Definition of contract

    private static final String DB_NAME ="Country.db";
    public static final String TABLE_NAME ="countries";
    public static final String ID = "_id";
    public static final String COUNTRY ="name";
    public static final String CURRENCY = "currency";
    private static final int VERSION =1;
    private static final String CREATE_TABLE =  " CREATE TABLE "+TABLE_NAME+ " ( "+ID  +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COUNTRY
         +" TEXT NOT NULL , "+ CURRENCY +" TEXT);";

    public DBHelper( @Nullable Context context) {
        super(context, DB_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL(CREATE_TABLE);
        Log.d("CREATE TABLE",CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }


//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//        Log.d("CREATE TABLE",CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
//        onCreate(db);
//
//    }
}
