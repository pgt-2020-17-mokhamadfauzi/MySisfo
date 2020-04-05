package com.example.mysisfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UbahPassword extends AppCompatActivity {
    EditText passwordLama, passwordBaru;
    Button btnUbah;
    Toolbar toolbar;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        btnUbah = findViewById(R.id.ubah);
        passwordLama = findViewById(R.id.passwordlama);
        passwordBaru = findViewById(R.id.passwordbaru);

        toolbar = findViewById(R.id.ubahpassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mNIM = user.get(sessionManager.NIM);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mPasswordLama = passwordLama.getText().toString().trim();
                String mPasswordBaru = passwordBaru.getText().toString().trim();
                if (!mPasswordLama.isEmpty() || !mPasswordBaru.isEmpty() ){
                    kirim_data(mNIM);
                }else{
                    passwordLama.setError("Please Input Your Current Password!");
                    passwordBaru.setError("Please Input Your New Password!");
                }
            }
        });
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

    void kirim_data(final String mNIM){
        String url = "https://sisfo-latihan.000webhostapp.com/ganti.php";
        StringRequest respon = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success1 = jsonObject.getString("status");
                    if (success1.equals("berhasil")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(UbahPassword.this);
                            builder.setTitle("Sukses");
                            builder.setMessage("Password Berhasil Diubah");
                            builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                    } else if (success1.equals("salah")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(UbahPassword.this);
                            builder.setTitle("Gagal");
                            builder.setMessage("Password Lama Salah!");
                            builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                        AlertDialog dialog1 = builder.create();
                        dialog1.show();
                    } else if (success1.equals("baru")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(UbahPassword.this);
                            builder.setTitle("Gagal");
                            builder.setMessage("Password Baru Tidak Boleh Sama Dengan Password Lama!");
                            builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                        AlertDialog dialog2 = builder.create();
                        dialog2.show();
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String>kirim_form = new HashMap<String, String>();
                kirim_form.put("nim", mNIM);
                kirim_form.put("passwordlama", passwordLama.getText().toString() );
                kirim_form.put("passwordbaru", passwordBaru.getText().toString() );
                return kirim_form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(UbahPassword.this);
        requestQueue.add(respon);
    }
}

