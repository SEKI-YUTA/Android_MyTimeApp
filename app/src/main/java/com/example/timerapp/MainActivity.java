package com.example.timerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.timerapp.Models.Alarm;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FragmentContainerView fragmentContainerView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainerView = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottomNav);


        // 初期状態だとフラグメントが何も表示されないのでとりあえずTimeFragmentを表示させておく
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new TimeFragment())
                .commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_time:
                        replaceFragment(new TimeFragment(), item);
                        break;
                    case R.id.nav_stopwatch:
                        replaceFragment(new StopWatchFragment(), item);
                        break;
                    case R.id.nav_timer:
                        replaceFragment(new TimerFragment(), item);
                        break;
                    case R.id.nav_alarm:
                        replaceFragment(new AlarmFragment(), item);
                        break;
                }
                return false;
            }
        });

    }

    private void replaceFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        item.setChecked(true);
    }
}