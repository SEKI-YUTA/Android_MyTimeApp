package com.example.timerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FragmentContainerView fragmentContainerView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainerView = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new TimeFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new TimeFragment())
                                .commit();
                        item.setChecked(true);

                        break;
                    case R.id.nav_setting:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new StopWatchFragment())
                                .commit();
                        item.setChecked(true);

                        break;
                    case R.id.nav_navigation:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new TimerFragment())
                                .commit();
                        item.setChecked(true);
                        break;
                }
                return false;
            }
        });


    }
}