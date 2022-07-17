package com.myapplicationdev.android.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {

    Button btnShowSong;
    ListView lv;
    ArrayAdapter<Songs> aa;
    ArrayList<Songs> al;
    Songs data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        btnShowSong = findViewById(R.id.btnShowSong);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long identity) {
                Songs song = al.get(position);
                Intent i = new Intent(ShowSong.this, ModifySong.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

        btnShowSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowSong.this);

                dbh.close();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(ShowSong.this);
        al.clear();
        al.addAll(dbh.getAllSong());
        aa.notifyDataSetChanged();


    }

    @Override
    protected void onStop() {
        super.onStop();

        finish();

    }
}