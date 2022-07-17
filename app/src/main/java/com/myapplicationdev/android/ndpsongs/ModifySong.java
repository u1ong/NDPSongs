package com.myapplicationdev.android.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModifySong extends AppCompatActivity {


    TextView tvSong, tvSinger, tvYear, tvStars, tvID;
    EditText etSinger, etSong, etYear;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Songs data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        tvID = findViewById(R.id.tvID);
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


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifySong.this);
                data.setSingers(etSinger.getText().toString());
                data.setTitle(etSong.getText().toString());

                dbh.updateSong(data);
                dbh.close();
            }
        });
    }
}