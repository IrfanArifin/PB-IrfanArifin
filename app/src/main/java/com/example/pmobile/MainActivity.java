package com.example.pmobile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextInputEditText emailUser, passwordUser;
    CheckBox ingatSaya;
    Button btnLogin;
    TextView lupaPassword, signUp;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Memproses login...");
        progressDialog.setCancelable(false);

        emailUser = findViewById(R.id.emailUser);
        passwordUser = findViewById(R.id.password);
        ingatSaya = findViewById(R.id.ingatSaya);
        btnLogin = findViewById(R.id.btnLogin);
        lupaPassword = findViewById(R.id.lupaPassword);
        signUp = findViewById(R.id.signUp);

        btnLogin.setOnClickListener(view -> {
            String email = emailUser.getText().toString().trim();
            String password = passwordUser.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                emailUser.setError("Masukkan email");
                emailUser.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                passwordUser.setError("Masukkan password");
                passwordUser.requestFocus();
            } else {
                loginUser(email, password);
            }
        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    private void loginUser(String email, String password) {
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            progressDialog.dismiss();
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null ) {
                    Toast.makeText(MainActivity.this, "Login berhasil", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(MainActivity.this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
