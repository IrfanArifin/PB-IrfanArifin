package com.example.pmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoActivity extends AppCompatActivity {

    EditText edtJudul, edtIsi;
    Button btnSimpan;
    RecyclerView recyclerView;
    MemoAdapter adapter;
    ArrayList<MemoModel> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        edtJudul = findViewById(R.id.edtJudul);
        edtIsi = findViewById(R.id.edtIsi);
        btnSimpan = findViewById(R.id.btnSimpan);
        recyclerView = findViewById(R.id.recyclerMemo);

        memoList = new ArrayList<>();
        adapter = new MemoAdapter(memoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnSimpan.setOnClickListener(view -> {
            String judul = edtJudul.getText().toString();
            String isi = edtIsi.getText().toString();
            if (!judul.isEmpty() && !isi.isEmpty()) {
                memoList.add(new MemoModel(judul, isi));
                adapter.notifyDataSetChanged();
                edtJudul.setText("");
                edtIsi.setText("");
            }
        });
    }
}
