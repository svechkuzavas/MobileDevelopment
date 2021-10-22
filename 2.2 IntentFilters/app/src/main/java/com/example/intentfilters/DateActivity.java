package com.example.intentfilters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        SimpleDateFormat template = new SimpleDateFormat("dd.MM.yyyy");
        String date = template.format(new Date(System.currentTimeMillis()));
        tv = (TextView) findViewById(R.id.tvDate);
        tv.setText(date);
    }
}