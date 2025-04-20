package com.example.pmobile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainNavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load default
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_container, new HomeFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.menu_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.menu_profile) {
                selectedFragment = new ProfileFragment();
            } else if (id == R.id.menu_settings) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
