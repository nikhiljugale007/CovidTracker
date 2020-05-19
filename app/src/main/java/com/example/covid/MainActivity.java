package com.example.covid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ,recyclerView2;
    ArrayList<MainModel> mainModel ;
    ArrayList<MainModel1> mainModel1;
    MainAdapter mainAdapter;
    MainAdapter1 mainAdapter1;
    TextView totalDeaths , totalRecovered, totalInfected;
    LinearLayout donate , donation ;
    ProgressBar progressBar;
    ImageButton btn , btnStat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = (RecyclerView)findViewById(R.id.recyler_view);
        recyclerView2 = (RecyclerView)findViewById(R.id.recyler_view2);

        totalDeaths = (TextView)findViewById(R.id.totalDeath);
        totalRecovered = (TextView)findViewById(R.id.totalRecovered);
        totalInfected = (TextView)findViewById(R.id.totalInfected);

        donate = (LinearLayout) findViewById(R.id.donate);
        btn = findViewById(R.id.btn);
        btnStat = findViewById(R.id.btnStat);
        progressBar = findViewById(R.id.progressBar);

        getData();

        Integer[] symptLogo = {R.drawable.cough,R.drawable.fever,R.drawable.sneez,R.drawable.throat,R.drawable.bodypain};
        String[] symptName = {"DRY COUGH","FEVER            ","SNEEZING","SOAR THROAT","Body Pain"};


        Integer[] prevLogo = {R.drawable.stayhome,R.drawable.socialdust,R.drawable.washhands,R.drawable.mask,R.drawable.india};
        String[] prevName = {"Stay Home","Social Distancing","Wash Hands Often","Use Mask","Follow Goverment Orders"};

        mainModel = new ArrayList<>();
        mainModel1 = new ArrayList<>();

        for(int i=0;i<symptLogo.length;i++){
            MainModel model = new MainModel(symptLogo[i],symptName[i]);
            mainModel.add(model);
        }
        for(int j=0;j<prevLogo .length;j++){
            MainModel1 model1 = new MainModel1(prevLogo[j],prevName[j]);
            mainModel1.add(model1);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager (MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager (MainActivity.this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager1);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        mainAdapter = new MainAdapter(MainActivity.this,mainModel);
        mainAdapter1 = new MainAdapter1(MainActivity.this,mainModel1);

        recyclerView.setAdapter(mainAdapter);
        recyclerView2.setAdapter(mainAdapter1);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,payment.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,visulizer.class);
                startActivity(intent);
            }
        });
        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StateCorona.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://disease.sh/v2/countries/india?yesterday=false&strict=true";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    totalInfected.setText(jsonObject.getString("cases"));
                    totalDeaths.setText(jsonObject.getString("deaths"));
                    totalRecovered.setText(jsonObject.getString("recovered"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR = " + error.toString(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                Log.d("Error response ",error.toString());
            }
        });

        queue.add(stringRequest);
    }
}
