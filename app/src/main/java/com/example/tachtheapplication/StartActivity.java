package com.example.tachtheapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

         startBtn = (Button)findViewById(R.id.startBtn);


        final Intent intent = new Intent(this, MainActivity.class);


        startBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startBtn.setEnabled(false);
                 Handler handler = new Handler();
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         startBtn.setEnabled(true);
                     }
                 }, 1000);

                 startActivity(intent);
             }
         });


        //dispatchKeyEventは押したときと離したときの２回検知される
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("webViewに戻ってもいいですか？").setTitle("ゲーム画面では戻ることはできません。");
        builder.show();
    }










}
