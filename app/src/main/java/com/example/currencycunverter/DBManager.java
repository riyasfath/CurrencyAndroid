package com.example.currencycunverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
    DBHelper dbHelper;
    SQLiteDatabase database;
    Context context;

    public DBManager(Context c){
        context= c;
    }
    public  DBManager open() {

        dbHelper = new DBHelper(context);
        database =dbHelper.getWritableDatabase();
        return this;
    }

    public  void close()
    {

        dbHelper.close();
    }
    public void Insert(String country,String currency){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COUNTRY,country);
        values.put(DBHelper.CURRENCY,currency);

        database.insert(DBHelper.TABLE_NAME,null,values);
        Log.d("Database Insert *******","*****************************************");
    }
    public Cursor fetch(){
        String [] columns = new String[] {
                DBHelper.ID,
                DBHelper.COUNTRY,
                DBHelper.CURRENCY
        };
        Cursor query = database.query(DBHelper.TABLE_NAME,
                columns,null,null,
                null,null,null);
        if (query != null){
            query.moveToFirst();
        }
        return query;

    }

    public void Update(Long id, String country, String currency) {
        ContentValues contentValues =new ContentValues();
        contentValues.put(DBHelper.COUNTRY,country);
        contentValues.put(DBHelper.CURRENCY,currency);
        database.update(DBHelper.TABLE_NAME,contentValues,DBHelper.ID+" ="+id,null);

    }

    public void Delete(Long id) {
        database.delete(DBHelper.TABLE_NAME,DBHelper.ID+" =?", new String[]{ id.toString()});




    }
}
