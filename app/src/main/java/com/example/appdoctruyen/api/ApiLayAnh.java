package com.example.appdoctruyen.api;

import android.os.AsyncTask;

import com.example.appdoctruyen.interfaces.LayAnhVe;
import com.example.appdoctruyen.interfaces.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data;
    LayAnhVe layAnhVe;
    String idChap;
    public ApiLayAnh(LayAnhVe layAnhVe,String idChap){
        this.layAnhVe=layAnhVe;
        this.idChap=idChap;

        this.layAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client= new OkHttpClient();
        Request request= new Request.Builder()
                .url("https://tranhuy28.000webhostapp.com/layAnh.php?idChap="+idChap)
                .build();
        data=null;
        try{
            Response response= client.newCall(request).execute();
            ResponseBody body= response.body();
            data=body.string();


        }catch (IOException e){
            data=null;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data==null) {
                this.layAnhVe.biLoi();
            }else {
                this.layAnhVe.ketThuc(data);
            }

    }
}
