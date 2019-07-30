package com.example.tachtheapplication;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

//  １引数  doInBackground() バックグラウンド処理の引数, 2引数 進捗度合いを表示したい時に使うパラメータ, 3引数　onPostExecute() 処理終了時に受け取る型
public class POstAccess extends AsyncTask<String, String, String> {

    private Activity ResultActivity;

    public POstAccess(Activity activity){
        this.ResultActivity = activity;
    }





        @Override                      //引数の型とparams
    protected String doInBackground(String... params) {

        String strUrl = params[0];
        String header = params[1];
        String body = params[2];

        String setParametor = "Header" + header + "Body" + body;



        HttpsURLConnection con =  null;
        InputStream is = null;
        String result = "";

        try {

            URL url = new URL(strUrl);
            con = (HttpsURLConnection) url.openConnection();

//接続タイムアウトを設定する。
            con.setConnectTimeout(100000);
//レスポンスデータ読み取りタイムアウトを設定する。
            con.setReadTimeout(100000);
//ヘッダーにUser-Agentを設定する。
            con.addRequestProperty("User-Agent", "Android");
//ヘッダーにAccept-Languageを設定する。
          //  con.addRequestProperty("Accept-Language", Locale.getDefault().toString());
//HTTPのメソッドをGETに設定する。
            con.setRequestMethod("POST");
//リクエストのボディ送信を許可しない
            con.setDoOutput(true);
            //postデータ送信処理時に無駄なメモリ使用、時間をかけない為にデータサイズを設定するか、不明なら0 で設定する。
            con.setChunkedStreamingMode(0);
            con.connect();

            //postデータ送信処理
            OutputStream os = null;

            try {
                os = con.getOutputStream();
                os.write(setParametor.getBytes("UTF-8"));
                //BufferWriterに出力するために必要
                os.flush();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if (os != null){
                    os.close();
                }
            }
            final int status = con.getResponseCode();
            if (status == HttpsURLConnection.HTTP_OK){
                result = "HTTP_OK";
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (con != null){
                con.disconnect();
            }
        }
        return result;
    }



    @Override
    protected void onPostExecute(String result){
            super.onPostExecute(result);





    }






}
