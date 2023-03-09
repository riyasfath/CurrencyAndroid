package com.example.currencycunverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView lv;
    List<String> simpleAdapter;
    private String m_Text = "";
    DBManager dbManager;
    final String[] fromDatabase =new String[]{
            DBHelper.ID,
            DBHelper.CURRENCY,
            DBHelper.COUNTRY
    };
    final int [] to = new int[]{
      R.id.custom_icon,
      R.id.custom_country,
      R.id.custom_currency
    };
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);

        ADDRecord addRecord=new ADDRecord(this);

        fab =(FloatingActionButton) findViewById(R.id.FAB);
        lv = (ListView) findViewById(R.id.list);
        registerForContextMenu(lv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Fab called",Snackbar.LENGTH_LONG).show();

                registerForContextMenu(lv);
                addRecord.show();



            }
        });
        fetchdatabase();


    }

    private void fetchdatabase() {
        dbManager.open();
        Cursor fetch = dbManager.fetch();
        dbManager.close();
adapter =new SimpleCursorAdapter(getApplicationContext(),R.layout.customlayout,fetch,fromDatabase,to);
    ListView listView= (ListView) findViewById(R.id.list);
    listView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
    dbManager.close();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){

            case R.id.context_menu_delete:
                Toast.makeText(this, "menu delete clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_edit:
                TextView lv_id = (TextView)  menuInfo.targetView.findViewById(R.id.custom_icon);

                TextView lv_country = (TextView)  menuInfo.targetView.findViewById(R.id.custom_country);
                TextView lv_currency = (TextView)  menuInfo.targetView.findViewById(R.id.custom_currency);
                String Id=lv_id.getText().toString();
                String Country = lv_country.getText().toString();
                String Currency = lv_currency.getText().toString();

                Intent modifyintent = new Intent(MainActivity.this,Edit.class);
                modifyintent.putExtra("Id",Id);
                Bundle bundle = new Bundle();
                bundle.putString("Id",Id);
                bundle.putString("Country",Country);
                bundle.putString( "Corrency",Currency);
                modifyintent.putExtra("bundle",bundle);
                startActivity(modifyintent);


                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }
//menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.options_menu_add) {
        }
        return super.onOptionsItemSelected(item);
    }


}