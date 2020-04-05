package com.example.mysisfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class ProfilActivity extends AppCompatActivity {
    Button btnUbah;
    private TextView nama, nim;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_dashboard);

        navView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_dashboard:
                        return;
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.navigation_notifications:
                        sessionManager.Logout();
                        return;
                }
                return;
            }
        });

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        nama = findViewById(R.id.txtNama);
        nim = findViewById(R.id.txtNIM);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mNama = user.get(sessionManager.NAMA);
        String mNIM = user.get(sessionManager.NIM);

        nama.setText(mNama);
        nim.setText(mNIM);

        btnUbah = findViewById(R.id.ubahpassword);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UbahPassword.class));
            }
        });

    }
}
