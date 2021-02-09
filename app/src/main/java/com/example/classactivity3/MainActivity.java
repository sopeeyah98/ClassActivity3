package com.example.classactivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button button_go;
    private EditText text_city;

    private static final String api_url = "http://api.openweathermap.org/data/2.5/forecast?";
    private static final String api_key = "7d07ae183f4ce7b8221e64a1ff90b978";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_city = findViewById(R.id.text_city);
        button_go = findViewById(R.id.button_go);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams params = new RequestParams();
                params.put("q", text_city.getText().toString());
                params.put("APPID", api_key);
                params.put("units","imperial");
                client.addHeader("Accept", "application/json");
                client.get(MainActivity.this, api_url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.d("response", new String(responseBody));
                        try {
                            JSONObject jsonObj = new JSONObject(new String(responseBody));
                            Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                            intent.putExtra("data", jsonObj.toString());
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("error", new String(responseBody));
                        Intent intent = new Intent(MainActivity.this, FailureActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });



    }
}