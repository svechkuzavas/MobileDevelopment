package com.example.dbusage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    Fragment fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_list:
                        fragment = ListFragment.newInstance(getSupportFragmentManager(), MainActivity.this);
                        break;
                    case R.id.navigation_add:
                        fragment = AddFragment.newInstance(getSupportFragmentManager(), MainActivity.this);
                        break;

                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_fragment, fragment).commit();
                return false;
            }
        });
    }
}