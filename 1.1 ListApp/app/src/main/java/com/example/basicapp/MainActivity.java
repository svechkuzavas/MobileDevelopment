package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuPresenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button update;
    TextView info;
    EditText inp;
    ListView list;
    ArrayAdapter arrAdapter;
    ArrayList arr = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update = (Button) findViewById(R.id.update);
        info = (TextView) findViewById(R.id.label);
        inp = (EditText) findViewById(R.id.text_inp);
        list = (ListView) findViewById(R.id.list);
        arrAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                arr);
        list.setAdapter(arrAdapter);
        View.OnClickListener update_name = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = inp.getText().toString() + " " + getResources().getString(R.string.question_template);
                info.setText(s.toCharArray(), 0, s.length());
                arr.add(inp.getText().toString());
                arrAdapter.notifyDataSetChanged();
            }
        };
        AdapterView.OnItemClickListener item_check = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("omg android", i + ":" + arr.get(i));
                String txt = arr.get(i).toString() + " " +getResources().getString(R.string.question_template);
                info.setText(txt.toCharArray(),0,txt.length());
            }
        };
        update.setOnClickListener(update_name);
        list.setOnItemClickListener(item_check);
    }
}