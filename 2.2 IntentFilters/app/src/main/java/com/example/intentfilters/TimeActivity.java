package com.example.intentfilters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        SimpleDateFormat template = new SimpleDateFormat("HH:mm:ss");
        String time = template.format(new Date(System.currentTimeMillis()));
        tv = (TextView) findViewById(R.id.tvTime);
        tv.setText(time);
    }
}