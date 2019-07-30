package com.example.tachtheapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView countDownText;
    ConstraintLayout countDownView;
    TextView countText;
    DateFormat  gamestartaDate;
    DateFormat limitGameDate;
    Double clearTime;
    TextView TimeView;
    String startDate = null;
    long start;
    long end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDownText = findViewById(countDownNumber);


        //ゲーム開始まで
        startGame();


    }

    //戻るボタンを押した後にも実行されるようにするにはここ。
    @Override
    protected void onResume() {
        super.onResume();

    }


    int countDownNumber = 0;

    private void  startGame(){

        countDownNumber = 3;


        countDownText = findViewById(R.id.countDownTextView);

        String te = String.valueOf(countDownText);

        countDownText.setText(te);

       final Timer timer = new Timer();
       final Timer resultTimer = new Timer();

        //ここで定期的に処理が行われる
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        countDown(timer);//ここでtimerがキャンセルされてゲームスタート
                        tapBtnmethod();


                    }
                });

            }    //1秒後に、 //1秒間隔で、(1000で1秒）
        }, 1000, 500);

        //timerGames.schedule( new T);

    }


    //ゲームスタートの際のカウントダウン
    private void countDown(Timer timer) {
        countDownNumber -= 1;
        countDownText.setText(String.valueOf(countDownNumber));

        countDownView = findViewById(R.id.countDownView);

        if (countDownNumber == 0) {
            timer.cancel();
            countDownView.setVisibility(View.INVISIBLE);
        }

    }




    int tapCount = 0;

    ImageView tapbtn;
    Timer gameTime;


    private void tapBtnmethod() {
        countText = findViewById(R.id.countView);
        start = System.currentTimeMillis();
        final Intent intent = new Intent(this, ResultActivity.class);


        tapbtn = findViewById(R.id.tapBtn);

        tapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tapCount ++;

                final String tapCountText = (String.valueOf(tapCount));
                countText.setText(tapCountText);// if は booleanを条件にする　

                gameTime = new  Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //ResultActivity に遷移する
                                //カウント結果（int)からハッシュ値を取得して16進数の文字列として生成
                                 String getHash = getHashValue(  "SHA-256",String.valueOf(tapCount));
                                 intent.putExtra( "RawData",String.valueOf(tapCount));
                                 intent.putExtra("HashData", getHash);
                                 startActivity(intent);
                                //外部webviewへの遷移
                               // Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.jp/"));
                               // startActivity(intent1);
                            }
                        });


                    }
                };

                gameTime.schedule(task,2500);
            }
        });
    }


    //backボタンの処理
    @Override
    public void onBackPressed(){

    }


    private String getHashValue(String algorithName, String value){
        MessageDigest md = null;
        StringBuilder sb = null;

        try{
            md = MessageDigest.getInstance(algorithName);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        md.update(value.getBytes());
        sb = new StringBuilder();
        for (byte b : md.digest()) {
            //byte値を16進数表記の文字列にする。
            String hex = String.format("%02x", b);
            sb.append(hex);
        }
        return sb.toString();
    }


}
