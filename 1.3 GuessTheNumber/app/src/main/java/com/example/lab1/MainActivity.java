package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView result;
    Button check;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        check = (Button) findViewById(R.id.check);
        input = (EditText) findViewById(R.id.input);
        Random random = new Random();
        int g_num = random.nextInt(100 - 1)  + 1;
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int in_num = 99999;
                try {
                    in_num = Integer.parseInt(input.getText().toString());
                } catch (Exception e) {
                    result.setText(getResources().getString(R.string.exception));
                }
                if (in_num != 99999) {
                    if (in_num < 1 || in_num > 100) {
                        result.setText(getResources().getString(R.string.out_of_range));
                    } else if (in_num < g_num) {
                        result.setText(getResources().getString(R.string.bigger));
                    } else if (in_num > g_num) {
                        result.setText(getResources().getString(R.string.less));
                    } else {
                        result.setText(getResources().getString(R.string.win));
                    }
                }
            }
        };
        check.setOnClickListener(onClickListener);
    }
}