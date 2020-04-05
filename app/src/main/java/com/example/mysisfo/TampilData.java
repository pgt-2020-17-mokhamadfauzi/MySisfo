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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TampilData extends AppCompatActivity {
    ArrayList<DataObject>list;
    ListView listView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        listView=findViewById(R.id.lisMahasiswa);
        toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tampil_data();
    }

    void tampil_data(){
        list = new ArrayList<>();
        String url = "https://sisfo-latihan.000webhostapp.com/tampilall.php";
        StringRequest respon = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject getData = jsonArray.getJSONObject(i);
                        String nim = getData.getString("nim");
                        String nama = getData.getString("nama");
                        String programstudi = getData.getString("programstudi");
                        String kelas = getData.getString("kelas");
                        String tempatlahir = getData.getString("tempatlahir");
                        list.add(new DataObject(nim,nama,programstudi,kelas,tempatlahir));
                    }
                    Adapter adapter = new Adapter(TampilData.this, list);
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
        RequestQueue requestQueue = Volley.newRequestQueue(TampilData.this);
        requestQueue.add(respon);
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

class Adapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<DataObject> model;
    public Adapter(Context context, ArrayList<DataObject>model){
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
    TextView nim, nama, programstudi, kelas , tempatlahir;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_data, parent, false);
        nim = view.findViewById(R.id.nim);
        nama = view.findViewById(R.id.nama);
        programstudi = view.findViewById(R.id.programstudi);
        kelas = view.findViewById(R.id.kelas);
        tempatlahir = view.findViewById(R.id.tempatlahir);

        nim.setText(model.get(position).getNim());
        nama.setText(model.get(position).getNama());
        programstudi.setText(model.get(position).getProgramstudi());
        kelas.setText(model.get(position).getKelas());
        tempatlahir.setText(model.get(position).getTempatlahir());

        return view;
    }
}
