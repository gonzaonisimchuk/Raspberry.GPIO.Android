package com.example.gonza.raspberrygpioandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String testService = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InvokeService("");
    }

    //Button clickButton = (Button) findViewById(R.id.button1);

    public void button1(View view) {
        InvokeService("p1");
    }

    public void button2(View view) {
        InvokeService("p2");
    }

    public void button3(View view) {
        InvokeService("p3");
    }

    public void button4(View view) {
        InvokeService("p4");
    }

    public void buttonBaño(View view) {
        InvokeService("Baño");
    }

    public void buttonGarage(View view) {
        InvokeService("Garage");
    }

    public void buttonPatio(View view) {
        InvokeService("Patio");
    }

    private void InvokeService(final String device) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("http://192.168.1.110:8080/" + device);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    testService = convertStreamToString(isw);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    TextView editText = (TextView) findViewById(R.id.labelPrincipal);
                    //String aaa = editText.getText().toString();
                    if (editText.getText().toString().equalsIgnoreCase("Conectando...")) {
                         editText.setText(testService);
                    }
                }
            }
        });
    }

    static String convertStreamToString(java.io.InputStreamReader is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
