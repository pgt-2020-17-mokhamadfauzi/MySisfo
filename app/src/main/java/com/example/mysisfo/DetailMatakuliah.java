package com.example.mysisfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailMatakuliah extends AppCompatActivity {
    Button btnAdd;
    EditText kodemk, namamk, hari, waktu, dosen;
    Toolbar toolbar;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_matakuliah);

        kodemk = findViewById(R.id.kodemk);
        namamk = findViewById(R.id.namamk);
        hari = findViewById(R.id.hari);
        waktu = findViewById(R.id.waktu);
        dosen = findViewById(R.id.dosen);
        btnAdd = findViewById(R.id.add);

        toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mNIM = user.get(sessionManager.NIM);

        kodemk.setText(getIntent().getStringExtra("kodemk"));
        namamk.setText(getIntent().getStringExtra("namamk"));
        hari.setText(getIntent().getStringExtra("hari"));
        waktu.setText(getIntent().getStringExtra("waktu"));
        dosen.setText(getIntent().getStringExtra("dosen"));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirim_data(mNIM);
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
        String url = "https://sisfo-latihan.000webhostapp.com/tambahmatkul.php";
        StringRequest respon = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success1 = jsonObject.getString("status");
                    if (success1.equals("berhasil")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailMatakuliah.this);
                        builder.setTitle("Sukses");
                        builder.setMessage("Jadwal Berhasil Ditambahkan");
                        builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
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
                kirim_form.put("kodemk", kodemk.getText().toString() );
                kirim_form.put("namamk", namamk.getText().toString() );
                kirim_form.put("hari", hari.getText().toString() );
                kirim_form.put("waktu", waktu.getText().toString() );
                kirim_form.put("dosen", dosen.getText().toString() );
                return kirim_form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMatakuliah.this);
        requestQueue.add(respon);
    }
}
