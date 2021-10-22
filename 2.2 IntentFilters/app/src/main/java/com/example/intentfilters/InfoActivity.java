package com.example.intentfilters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent =getIntent();
        String format = "", additionalText = "";
        String name = intent.getStringExtra("lastName");
        if (intent.getAction().equals("com.example.intent.action.showtime")){
            additionalText = "Time: ";
            format = "HH:mm:ss";
        } else if (intent.getAction().equals("com.example.intent.action.showdate")){
            additionalText = "Date: ";
            format = "dd.MM.yyyy";
        }
        SimpleDateFormat template = new SimpleDateFormat(format);
        String outp = template.format(new Date(System.currentTimeMillis()));

        TextView tv = (TextView)  findViewById(R.id.info);
        tv.setText(additionalText + outp);
        TextView tvName = (TextView) findViewById(R.id.name);
        tvName.setText(name);
    }
}