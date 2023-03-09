package com.example.currencycunverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public  class Edit extends AppCompatActivity  implements View.OnClickListener {

   TextView edit_id;
   EditText edit_country;
   EditText edit_currency;
   DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        Bundle extras  =intent.getBundleExtra("bundle");
        String id =extras.getString("Id");
        String country =extras.getString("Country");
        String corrency =extras.getString("Corrency");
         edit_id =(TextView) findViewById(R.id.textView);
         edit_country =(EditText) findViewById(R.id.Title);
        edit_currency =(EditText) findViewById(R.id.disc);
        edit_id.setText(id);
        edit_currency.setText(corrency);
        edit_country.setText(country);
         Button update = (Button) findViewById(R.id.update);
        Button delete=(Button) findViewById(R.id.delete);
      update.setOnClickListener(this);
      delete.setOnClickListener(this);
      dbManager=new DBManager(this);
      dbManager.open();

    }

    @Override
    public void onClick(View view) {
        Long id=Long.valueOf(edit_id.getText().toString());
        String country = edit_country.getText().toString();
        String currency = edit_currency.getText().toString();
        switch (view.getId())
        {
                case R.id.update:
                    dbManager.Update(id,country,currency);
                    dbManager.close();
                    Returnhome();
                break;
                case R.id.delete:
                    dbManager.Delete(id);
                    dbManager.close();

                    Returnhome();


                    break;


        }
    }

    private void Returnhome() {
        Intent i=new Intent(Edit.this,MainActivity.class);
        startActivity(i);
    }
}