package com.example.mysisfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RekapNilai extends AppCompatActivity {
    Button semester1, semester2, semester3, semester4, semester5, semester6;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekap_nilai);

        semester1=findViewById(R.id.semester1);
        semester2=findViewById(R.id.semester2);
        semester3=findViewById(R.id.semester3);
        semester4=findViewById(R.id.semester4);
        semester5=findViewById(R.id.semester5);
        semester6=findViewById(R.id.semester6);

        semester1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SemesterSatu.class));
            }
        });

        semester2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SemesterDua.class));
            }
        });

        semester3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SemesterTiga.class));
            }
        });

        semester4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SemesterEmpat.class));
            }
        });

        semester5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SemesterLima.class));
            }
        });

        semester6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SemesterEnam.class));
            }
        });

        toolbar=findViewById(R.id.tollbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
