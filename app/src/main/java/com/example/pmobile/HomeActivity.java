package com.example.pmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        CardView cardProfile = findViewById(R.id.cardProfile);
        CardView cardSettings = findViewById(R.id.cardSettings);
        CardView cardLogout = findViewById(R.id.cardLogout);
        TextView tvWelcome = findViewById(R.id.tvWelcome);

        // Inisialisasi Firebase
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        tvWelcome.setText("Selamat Datang di Aplikasi");

        cardProfile.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        cardSettings.setOnClickListener(view -> {
            // Tambahkan aksi untuk setting di sini
        });

        cardLogout.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
