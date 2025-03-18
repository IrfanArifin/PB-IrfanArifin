package com.example.pmobile.Models;

public class UserDetails {
    private String uid;
    private String username;
    private String userEmail;
    private String userPassword; // Jika ingin disimpan (opsional)
    private String userNIM;

    // Konstruktor kosong diperlukan oleh Firebase
    public UserDetails() {
    }

    public UserDetails(String uid, String username, String userEmail, String userPassword, String userNIM) {
        this.uid = uid;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNIM = userNIM;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserNIM() {
        return userNIM;
    }
}
