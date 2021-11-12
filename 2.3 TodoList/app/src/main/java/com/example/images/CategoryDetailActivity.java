package com.example.images;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.images.databinding.TodoAddDialogBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends AppCompatActivity implements TodoAddDialog.TodoAddDialogListener {
    Button save, add;
    ListView list;
    ArrayAdapter arrAdapter;
    ArrayList<String> todos = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        save = findViewById(R.id.save);
        add = findViewById(R.id.add_todo);

        list = (ListView) findViewById(R.id.todo_list);
        arrAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                todos);
        list.setAdapter(arrAdapter);

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        // deserializing chosen document
        ArrayList<String> received = gson.fromJson(getIntent().getStringExtra("todos"), type);
        todos.addAll(received);
        arrAdapter.notifyDataSetChanged();

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.save:
                        Intent intent = new Intent(CategoryDetailActivity.this, MainActivity.class);
                        intent.putExtra("category", getIntent().getStringExtra("category"));
                        Gson gson = new Gson();
                        String todosJSON = gson.toJson(todos);
                        intent.putExtra("todos", todosJSON);
                        setResult(2, intent);
                        finish();
                        break;
                    case R.id.add_todo:
                        TodoAddDialog dialog = TodoAddDialog.newInstance();
                        dialog.show(getSupportFragmentManager(), " ");
                        break;
                }
            }
        };

        AdapterView.OnItemClickListener update = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        };

        save.setOnClickListener(click);
        add.setOnClickListener(click);
        list.setOnItemClickListener(update);
    }

    @Override
    public void onDialogPositiveClick(String todo) {
        todos.add(todo);
        arrAdapter.notifyDataSetChanged();
    }
}