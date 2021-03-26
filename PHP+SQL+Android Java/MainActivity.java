package com.example.myapplication;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;



import static android.os.Build.VERSION_CODES.O;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(mutiThread);
                thread.start();
            }
        });

    }


    private Runnable mutiThread = new Runnable() {
        public void run() {
            try {
                URL url = new URL("http://192.168.103.122/sensor.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream =
                            connection.getInputStream();
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);

                    String box = "";
                    String line = null;
                    while ((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                    }
                    inputStream.close();
                    result = box;
                }

            } catch (Exception e) {
                result = e.toString();
            }

            runOnUiThread(new Runnable() {
                public void run() {
                    textView.setText(result);
                }
            });
        }

    };

}

