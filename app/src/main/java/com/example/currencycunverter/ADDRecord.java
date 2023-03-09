package com.example.currencycunverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class ADDRecord extends Dialog implements View.OnClickListener {
   EditText t1,t2;
   Button button;
   private  DBHelper dbHelper;
   public Activity c;
   DBManager dbManager;
   String s1,s2;

    public ADDRecord(@NonNull Activity content) {
        super(content);
        this.c=content;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addrecord);
        t1 =(EditText) findViewById(R.id.edit);
        t2 =(EditText) findViewById(R.id.edit1);
        button=(Button) findViewById(R.id.button);
       button.setOnClickListener(this);


        dbManager = new DBManager(c);
        dbManager.open();

            }

    @Override
    public void onClick(View view) {
        s1 = String.valueOf(t1.getText());
        s2 =String.valueOf(t2.getText());

        dbManager.Insert(s1,s2);
        dbManager.close();
        dismiss();

        Intent intent=new Intent(c,MainActivity.class);
        c.startActivity(intent);

    }






    }

