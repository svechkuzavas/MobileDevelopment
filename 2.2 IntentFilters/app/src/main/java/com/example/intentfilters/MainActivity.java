package com.example.intentfilters;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button time = (Button) findViewById(R.id.time);
        Button date = (Button) findViewById(R.id.date);
        EditText edt = (EditText) findViewById(R.id.edtLN);
        View.OnClickListener activitySwitcher = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chosenActivity;
                switch (view.getId()){
                    case R.id.time:
                        chosenActivity = new Intent("com.example.intent.action.showtime");
                        chosenActivity.putExtra("lastName", edt.getText().toString());
                        startActivity(chosenActivity);
                        break;
                    case R.id.date:
                        chosenActivity = new Intent("com.example.intent.action.showdate");
                        chosenActivity.putExtra("lastName", edt.getText().toString());
                        startActivity(chosenActivity);
                        break;
                    default:
                        break;
                }
            }
        };
        time.setOnClickListener(activitySwitcher);
        date.setOnClickListener(activitySwitcher);
    }

}