package com.example.mysisfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class TampilMatakuliah extends AppCompatActivity {
    ArrayList<DataMatakuliah> list;
    ListView listView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_matakuliah);

        listView=findViewById(R.id.lisMatakuliah);
        toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tampil_data();

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

    void tampil_data(){
        list = new ArrayList<>();
        String url = "https://sisfo-latihan.000webhostapp.com/tampilmatakuliah.php";
        StringRequest respon = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject getData = jsonArray.getJSONObject(i);
                        String kodemk = getData.getString("kodemk");
                        String namamk = getData.getString("namamk");
                        String hari = getData.getString("hari");
                        String waktu = getData.getString("waktu");
                        String dosen = getData.getString("dosen");
                        list.add(new DataMatakuliah(kodemk,namamk,hari,waktu,dosen));
                    }
                    Adapter7 adapter = new Adapter7(TampilMatakuliah.this, list);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(TampilMatakuliah.this);
        requestQueue.add(respon);
    }
}

class Adapter7 extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<DataMatakuliah> model;
    public Adapter7(Context context, ArrayList<DataMatakuliah>model){
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
    TextView kodemk, namamk, hari, waktu , dosen;
    Button btnDetail;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_matakuliah, parent, false);
        btnDetail = view.findViewById(R.id.detail);
        kodemk = view.findViewById(R.id.kodemk);
        namamk = view.findViewById(R.id.namamk);
        hari = view.findViewById(R.id.hari);
        waktu = view.findViewById(R.id.waktu);
        dosen = view.findViewById(R.id.dosen);

        kodemk.setText(model.get(position).getKodemk());
        namamk.setText(model.get(position).getNamamk());
        hari.setText(model.get(position).getHari());
        waktu.setText(model.get(position).getWaktu());
        dosen.setText(model.get(position).getDosen());

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMatakuliah.class);

                intent.putExtra("kodemk", model.get(position).getKodemk());
                intent.putExtra("namamk", model.get(position).getNamamk());
                intent.putExtra("hari", model.get(position).getHari());
                intent.putExtra("waktu", model.get(position).getWaktu());
                intent.putExtra("dosen", model.get(position).getDosen());
                context.startActivity(intent);
            }
        });

        return view;
    }
}