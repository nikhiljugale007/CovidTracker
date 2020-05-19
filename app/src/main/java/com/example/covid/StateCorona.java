package com.example.covid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StateCorona extends AppCompatActivity {

    private TextView textView , mhTextView;
    private Button btn;
    private RequestQueue mQueue ;
    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_corona);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textView = (TextView)findViewById(R.id.textView);
        mhTextView = (TextView)findViewById(R.id.mhCorona);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mQueue = Volley.newRequestQueue(this);

        progressBar.setVisibility(View.GONE);
        jsonParse();
        //progressBar.setVisibility(View.INVISIBLE);
    }

    private void jsonParse() {
        String url = "http://covid19-india-adhikansh.herokuapp.com/states";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("state");

                            for(int i=0 ; i < jsonArray.length() ; i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                String stateName = object.getString("name");
                                int confirmed = object.getInt("confirmed");
                                int recovered = object.getInt("cured");
                                int deaths = object.getInt("death");

                                String nikhil = "Maharashtra";
                                if(stateName.equals(nikhil)){
                                    mhTextView.setText("");
                                    mhTextView.append("STATE          : " + stateName + "\n" + "CASES         : "+ String.valueOf(confirmed) + "\n"+  "RECOVERED   : "+ String.valueOf(recovered) +"\n" + "DEATHS       : "+ String.valueOf(deaths) );
                                }

                                textView.append(" STATE : " + stateName + "\n" + "CONFIRMED CASES: "+ String.valueOf(confirmed) + "\n"+  "RECOVERED : "+ String.valueOf(recovered) +"\n" + "DEATHS : "+ String.valueOf(deaths) +
                                         "\n\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Unable to load the data ",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(StateCorona.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
