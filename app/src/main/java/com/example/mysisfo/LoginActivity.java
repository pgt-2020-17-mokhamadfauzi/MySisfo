package com.example.mysisfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class LoginActivity extends AppCompatActivity {
    private EditText nim, password;
    private Button btnLogin;
    private ProgressBar loading;
    private static String URL_LOGIN = "https://duhiuas.000webhostapp.com/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        nim = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loading = findViewById(R.id.loading);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = nim.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if (!mUsername.isEmpty() || !mPassword.isEmpty()){
                    //Login(mUsername, mPassword);
                    kirim_data();
                }else{
                    nim.setError("Please Set Your Username!");
                    password.setError("Please Insert Your Password");
                }
            }
        });
    }
    void kirim_data(){
        loading.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);
        String url = "https://sisfo-latihan.000webhostapp.com/login.php";
        StringRequest respon = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success1 = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success1.equals("1")){
                        for (int i=0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String nim = object.getString("nim").trim();
                            String nama = object.getString("nama").trim();

                            sessionManager.createSession(nim, nama);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("nama", nama);
                            intent.putExtra("nim", nim);
                            startActivity(intent);
                        }
                    }else if (success1.equals("0")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Gagal");
                        builder.setMessage("Username Atau Password Salah!");
                        builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                loading.setVisibility(View.GONE);
                                btnLogin.setVisibility(View.VISIBLE);
                            }
                        });
                        AlertDialog dialog2 = builder.create();
                        dialog2.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loading.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
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
            protected Map<String, String>getParams(){
                Map<String, String>kirim_form = new HashMap<String, String>();
                kirim_form.put("nim", nim.getText().toString() );
                kirim_form.put("password", password.getText().toString() );
                return kirim_form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(respon);
    }
}
