package com.example.secondactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Activity 1 Logs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() callback entry");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choose_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.act_1);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent chosenActivity;
        switch (item.getItemId()){
            case R.id.act_2:
                chosenActivity = new Intent(this, MainActivity2.class);
                break;
            case R.id.act_3:
                chosenActivity = new Intent(this, MainActivity3.class);
                break;
            case R.id.act_4:
                chosenActivity = new Intent(this, MainActivity4.class);
                break;
            default:
                chosenActivity = new Intent();
                break;
        }
        startActivity(chosenActivity);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() callback entry");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() callback entry");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() callback entry");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() callback entry");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() callback entry");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() callback entry");
    }

}