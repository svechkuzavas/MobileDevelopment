package com.example.images;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryAddDialog.CategoryAddDialogListener {
    Button add;
    ListView list;
    ArrayAdapter arrAdapter;
    ArrayList<String> categories = new ArrayList();
    HashMap<String,ArrayList<String>> tasks = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add_cat);

        list = (ListView) findViewById(R.id.cat_list);
        arrAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                categories);
        list.setAdapter(arrAdapter);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryAddDialog dialog = CategoryAddDialog.newInstance();
                dialog.show(getSupportFragmentManager(), " ");
            }
        };

        AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Gson gson = new Gson();
                String catRecords = gson.toJson(tasks.get(categories.get(i)));
                Intent intent = new Intent(MainActivity.this,CategoryDetailActivity.class);
                intent.putExtra("category", categories.get(i).toString());
                intent.putExtra("todos", catRecords);
                startActivityForResult(intent, 2);
            }
        };

        list.setOnItemClickListener(onItemClick);
        add.setOnClickListener(click);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>(){}.getType();
            // deserializing chosen document
            ArrayList<String> received = gson.fromJson(data.getStringExtra("todos"), type);
            Log.d("", received.toString());
            tasks.put(data.getStringExtra("category"), received);
        }
    }

    @Override
    public void onDialogPositiveClick(String catName) {
        categories.add(catName);
        tasks.put(catName, new ArrayList<String>());
        arrAdapter.notifyDataSetChanged();
    }
}