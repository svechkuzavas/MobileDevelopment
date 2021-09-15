package com.example.simpleclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button increase, decrease, reset;
    TextView text;
    private long score = 0;
    public void reformat(TextView tv) {
        String s = getResources().getString(R.string.clicks) + score;
        tv.setText(s.toCharArray(),0,s.length());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        increase = (Button) findViewById(R.id.increase);
        decrease = (Button) findViewById(R.id.decrease);
        reset = (Button) findViewById(R.id.reset);
        text = (TextView) findViewById(R.id.start_text);
        View.OnClickListener decreaseL = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score--;
                reformat(text);
            }
        };
        View.OnClickListener increaseL = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                reformat(text);
            }
        };
        View.OnClickListener resetL = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                reformat(text);
            }
        };
        increase.setOnClickListener(increaseL);
        decrease.setOnClickListener(decreaseL);
        reset.setOnClickListener(resetL);
    }
}