package com.example.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button update, ok, cancel, sort, duplicates;
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
        cancel = (Button) findViewById(R.id.cancel);
        ok = (Button) findViewById(R.id.ok);
        sort = (Button) findViewById(R.id.sort);
        duplicates = (Button) findViewById(R.id.duplicates);

        info = (TextView) findViewById(R.id.label);
        inp = (EditText) findViewById(R.id.text_inp);
        list = (ListView) findViewById(R.id.list);
        arrAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                arr);
        list.setAdapter(arrAdapter);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.update:
                        String s = inp.getText().toString() + " " + getResources().getString(R.string.question_template);
                        info.setText(s.toCharArray(), 0, s.length());
                        arr.add(inp.getText().toString());
                        arrAdapter.notifyDataSetChanged();
                        inp.setText("");
                        break;
                    case R.id.ok:
                        Toast.makeText(getApplicationContext(), "Нажата кнопка Ок", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.cancel:
                        Toast.makeText(getApplicationContext(), "Нажата кнопка Cancel", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.sort:
                        Collections.sort(arr);
                        arrAdapter.notifyDataSetChanged();
                        break;
                    case R.id.duplicates:
                        Set<String> unique = new LinkedHashSet<String>();
                        unique.addAll(arr);
                        arr.clear();
                        arr.addAll(unique);
                        arrAdapter.notifyDataSetChanged();
                    default:
                        break;
                }
            }
        };
        AdapterView.OnItemClickListener item_check = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String txt = arr.get(i).toString() + " " +getResources().getString(R.string.question_template);
                info.setText(txt.toCharArray(),0,txt.length());
            }
        };
        AdapterView.OnItemLongClickListener item_del = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arr.remove(i);
                arrAdapter.notifyDataSetChanged();
                return true;
            }
        };
        update.setOnClickListener(click);
        ok.setOnClickListener(click);
        cancel.setOnClickListener(click);
        sort.setOnClickListener(click);
        duplicates.setOnClickListener(click);
        list.setOnItemClickListener(item_check);
        list.setOnItemLongClickListener(item_del);
    }
}