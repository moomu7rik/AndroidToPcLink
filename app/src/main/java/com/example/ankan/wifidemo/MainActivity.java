package com.example.ankan.wifidemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread myThread = new Thread(new MyServerThread());
myThread.start();

    }
}
class MyServerThread implements Runnable
{
    Socket s;
    ServerSocket ss;
    InputStreamReader isr;
    BufferedReader bufferedReader;
   Handler h = new Handler();
    String message;

    @Override
    public void run() {
        try {
            ss=new ServerSocket(7801);
            while (true)
            {
               s=ss.accept();
               isr= new InputStreamReader((s.getInputStream()));
               bufferedReader=new BufferedReader(isr);
               message=bufferedReader.readLine();

               h.post(new Runnable() {
                   @Override
                   public void run() {
                       Log.i("Message Received", message);
                   }
               });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}