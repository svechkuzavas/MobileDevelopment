package com.example.dbusage;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment{
    FragmentManager fm;
    Context context;
    DBHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<Model> records = new ArrayList<>();

    RecyclerView todoListView;
    RecyclerView.LayoutManager todoListLayoutManager;
    ModelAdapter todoListAdapter;
    public ListFragment(FragmentManager fm, Context context) {
        this.fm = fm;
        this.context = context;
    }
    public static ListFragment newInstance(FragmentManager fm, Context context) {
        ListFragment fragment = new ListFragment(fm, context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoListView = (RecyclerView) view.findViewById(R.id.todo_list);
        todoListView.setHasFixedSize(true);
        todoListLayoutManager = new LinearLayoutManager(context);
        todoListView.setLayoutManager(todoListLayoutManager);
        todoListAdapter = new ModelAdapter();
        todoListView.setAdapter(todoListAdapter);
        Cursor c = db.query("TODOS", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int headerColIndex = c.getColumnIndex("header");
            int timeColIndex = c.getColumnIndex("time");
            int dateColIndex = c.getColumnIndex("data");
            do {
                records.add(new Model(c.getString(headerColIndex),
                        c.getString(dateColIndex),
                        c.getString(timeColIndex)));
            } while (c.moveToNext());
        } else Log.d("LOG_TAG", "0 rows");
        todoListAdapter.setList(records);
    }

    @Override
    public void onResume() {
        super.onResume();
        records.clear();
        Cursor c = db.query("TODOS", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int headerColIndex = c.getColumnIndex("header");
            int timeColIndex = c.getColumnIndex("time");
            int dateColIndex = c.getColumnIndex("data");
            do {
                records.add(new Model(c.getString(headerColIndex),
                        c.getString(dateColIndex),
                        c.getString(timeColIndex)));
            } while (c.moveToNext());
        } else Log.d("LOG_TAG", "0 rows");
        todoListAdapter.setList(records);
        c.close();
    }
}

