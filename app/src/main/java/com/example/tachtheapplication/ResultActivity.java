package com.example.tachtheapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //findviewByIdはメンバ変数にしたらだめ　onCreateなどにあるようにsetContentView(R.layout...)　がないと、layout に紐付けられないから
        //文字列
        TextView RawDataText = findViewById(R.id.resultText);
        //Hash値
        TextView HashDataText = findViewById(R.id.hashDataText);


        //今回の場合、btnタップしないでResultActivityが起動したら、intentはnullになる。
        Intent intent = getIntent();

        if (intent != null){
            String text = intent.getStringExtra("RawData");
            RawDataText.setText(text);
            String hash = intent.getStringExtra("HashData");
            HashDataText.setText(hash);
        }else {
            RawDataText.setText("nullです。");
        }



        //値とhash値をまとめてjsonにする
        //JSON.parse();
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("webViewに戻ってもいいですか？").setTitle("ゲーム画面では戻ることはできません。");
        builder.show();
    }

}
