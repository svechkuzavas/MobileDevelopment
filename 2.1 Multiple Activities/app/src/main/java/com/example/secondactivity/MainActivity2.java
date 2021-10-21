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

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "Activity 2 Logs";
    Button btnGt1, btnGt3, btnGt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate() callback entry");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choose_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.act_2);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent chosenActivity;
        switch (item.getItemId()){
            case R.id.act_1:
                chosenActivity = new Intent(this, MainActivity.class);
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
}