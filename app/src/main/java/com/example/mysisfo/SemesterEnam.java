package com.example.mysisfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SemesterEnam extends AppCompatActivity {

    ArrayList<NilaiSatu> list;
    ListView listView;
    Toolbar toolbar;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_enam);

        listView=findViewById(R.id.listSemester6);
        toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mNIM = user.get(sessionManager.NIM);

        kirim_data(mNIM);
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

    void kirim_data(final String mNIM) {
        final String angka = "6";
        list = new ArrayList<>();
        String url = "https://sisfo-latihan.000webhostapp.com/rekap.php";
        StringRequest respon = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String kodemk = object.getString("kodemk").trim();
                        String namamk = object.getString("namamk").trim();
                        String sks = object.getString("sks").trim();
                        String huruf = object.getString("huruf").trim();
                        String akhir = object.getString("akhir").trim();
                        list.add(new NilaiSatu(kodemk, namamk, sks, huruf, akhir));
                    }

                    Adapter6 adapter = new Adapter6(SemesterEnam.this, list);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> kirim_form = new HashMap<String, String>();
                kirim_form.put("nim", mNIM);
                kirim_form.put("angka", angka);
                return kirim_form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SemesterEnam.this);
        requestQueue.add(respon);
    }
}
class Adapter6 extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<NilaiSatu> model;
    public Adapter6(Context context, ArrayList<NilaiSatu> model){
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    TextView kodemk, namamk, sks, huruf , akhir;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_nilaisatu, parent, false);
        kodemk = view.findViewById(R.id.kodemk);
        namamk = view.findViewById(R.id.namamk);
        sks = view.findViewById(R.id.sks);
        huruf = view.findViewById(R.id.huruf);
        akhir = view.findViewById(R.id.akhir);

        kodemk.setText(model.get(position).getKodemk());
        namamk.setText(model.get(position).getNamamk());
        sks.setText(model.get(position).getSks());
        huruf.setText(model.get(position).getHuruf());
        akhir.setText(model.get(position).getAkhir());

        return view;
    }
}

