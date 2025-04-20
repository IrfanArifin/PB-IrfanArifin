package com.example.pmobile;

public class MemoModel {
    String judul, isi;

    public MemoModel(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }

    public String getJudul() { return judul; }
    public String getIsi() { return isi; }
}
