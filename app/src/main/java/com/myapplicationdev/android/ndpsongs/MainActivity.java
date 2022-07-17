package com.myapplicationdev.android.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnList;
    TextView tvSong, tvSinger, tvYear, tvStars;
    EditText etSinger, etSong, etYear;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;
    Songs data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnUpdate);
        btnList = findViewById(R.id.btnDelete);
        tvSong = findViewById(R.id.tvSong);
        tvSinger = findViewById(R.id.tvSinger);
        tvYear = findViewById(R.id.tvYear);
        tvStars = findViewById(R.id.tvStars);
        etSinger = findViewById(R.id.etSinger);
        etSong = findViewById(R.id.etSong);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this,
                android.R.layout.simple_list_item_1, al);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        ShowSong.class);
                startActivity(i);
            }

        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String data = etSong.getText().toString();
                String data1 = etSinger.getText().toString();
                String data2 = etYear.getText().toString();
                int data3 = radioGroup.getCheckedRadioButtonId();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(data,data1,data2,data3);

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSong());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}