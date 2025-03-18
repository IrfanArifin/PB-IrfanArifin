package com.example.pmobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmobile.Models.UserDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvNamaPengguna, tvEmailPengguna, tvNimPengguna;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Ganti dengan layout Anda

        // Inisialisasi TextViews
        tvNamaPengguna = findViewById(R.id.tvNamaPengguna); // Ganti dengan ID yang benar
        tvEmailPengguna = findViewById(R.id.tvEmailPengguna); // Ganti dengan ID yang benar
        tvNimPengguna = findViewById(R.id.tvNimPengguna); // Ganti dengan ID yang benar

        // Dapatkan pengguna yang saat ini masuk
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            // Dapatkan referensi database
            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

            // Mengambil data pengguna dari Firebase
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d("ProfileActivity", "Data diterima: " + snapshot.getValue());

                    if (snapshot.exists()) {
                        // Mengonversi snapshot menjadi objek UserDetails
                        UserDetails userDetails = snapshot.getValue(UserDetails.class);
                        if (userDetails != null) {
                            tvNamaPengguna.setText("Nama Pengguna: " + userDetails.getUsername());
                            tvEmailPengguna.setText("Email Pengguna: " + userDetails.getUserEmail());
                            tvNimPengguna.setText("NIM Pengguna: " + userDetails.getUserNIM());
                        }
                    } else {
                        Toast.makeText(ProfileActivity.this, "Data pengguna tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Penanganan kesalahan jika pembacaan database dibatalkan
                    Log.e("ProfileActivity", "Database error: " + error.getMessage());
                    Toast.makeText(ProfileActivity.this, "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Tidak ada pengguna yang masuk
            Toast.makeText(ProfileActivity.this, "Tidak ada pengguna yang masuk", Toast.LENGTH_SHORT).show();
            // Mungkin arahkan pengguna ke layar login
        }
    }
}
