package com.example.pmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmobile.Models.UserDetails;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    Button btnSignUp;
    TextInputEditText usernameSignUp, passwordSignUp, nimPengguna, emailPengguna;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        emailPengguna = findViewById(R.id.emailPengguna);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        nimPengguna = findViewById(R.id.nimPengguna);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mendaftarkan akun...");
        progressDialog.setCancelable(false);

        btnSignUp.setOnClickListener(view -> {
            String username = String.valueOf(usernameSignUp.getText()).trim();
            String email = String.valueOf(emailPengguna.getText()).trim();
            String password = String.valueOf(passwordSignUp.getText()).trim();
            String NIM = String.valueOf(nimPengguna.getText()).trim();

            if (TextUtils.isEmpty(username)) {
                usernameSignUp.setError("Masukkan username");
                usernameSignUp.requestFocus();
            } else if (TextUtils.isEmpty(email)) {
                emailPengguna.setError("Masukkan email");
                emailPengguna.requestFocus();
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailPengguna.setError("Format email tidak valid");
                emailPengguna.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                passwordSignUp.setError("Masukkan password");
                passwordSignUp.requestFocus();
            } else if (password.length() < 6) {
                passwordSignUp.setError("Password minimal 6 karakter");
                passwordSignUp.requestFocus();
            } else if (TextUtils.isEmpty(NIM)) {
                nimPengguna.setError("Masukkan NIM");
                nimPengguna.requestFocus();
            } else {
                registerUser(username, email, password, NIM);
            }
        });
    }

    private void registerUser(String username, String email, String password, String NIM) {
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity2.this, task -> {
            progressDialog.dismiss();
            if (task.isSuccessful()) {
                FirebaseUser fUser = auth.getCurrentUser();
                if (fUser != null) {
                    String uid = fUser.getUid();
                    UserDetails userDetails = new UserDetails(uid, username, email, password, NIM);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

                    reference.child(uid).setValue(userDetails)
                            .addOnSuccessListener(aVoid -> {  // Jika berhasil
                                Toast.makeText(MainActivity2.this, "Akun berhasil dibuat!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(e -> {  // Jika gagal
                                Log.e(TAG, "Gagal menyimpan data ke Firebase Database", e);
                                Toast.makeText(MainActivity2.this, "Gagal menyimpan data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            });


                }
            } else {
                Log.e(TAG, "Gagal mendaftarkan akun", task.getException());
                Toast.makeText(MainActivity2.this, "Pendaftaran gagal: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
