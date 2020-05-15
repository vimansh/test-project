package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RequestQueue mQueue;
    Button button;
    String[][] ba=new String[100][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button =findViewById(R.id.button);

        mQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }
    private void jsonParse() {
        String url ="http://www.mocky.io/v2/5cc008de310000440a035fdf";
        textView.setText("");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    //        textView.append("llllll");
                        try {
                            JSONArray jsonArray = response.getJSONArray("book_array");

                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject book_array = jsonArray.getJSONObject(i);

                                String book_title = book_array.getString("book_title");
                                String image = book_array.getString("image");
                                String author = book_array.getString("author");

                                ba[i][0] = book_title;
                                ba[i][1] = image;
                                ba[i][2] = author;

                                textView.append(book_title + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
       // textView.append("ddddd");
        /*for(int j=0;j<ba.length;j++)
        {
            for(int k=0;k<3;k++) {
                textView.append(ba[j][k] + ", ");
            }
            textView.append("\n");
        }*/

        //final Request<JSONObject> add = mQueue.add(request);
        //textView.append("jjjjjj");
    }
}
