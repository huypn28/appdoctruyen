package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appdoctruyen.adapter.TruyenTranhAdapter;
import com.example.appdoctruyen.api.ApiLayAnh;
import com.example.appdoctruyen.api.ApiLayTruyen;
import com.example.appdoctruyen.interfaces.LayAnhVe;
import com.example.appdoctruyen.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe {
ImageView imgAnh;
ArrayList<String> arrUrlAnh;
int soTrang, soTrangDangDoc;
TextView txvSoTrang;
String idChap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayAnh(this,idChap).execute();
    }
    private void init(){
        Bundle b=getIntent().getBundleExtra("data");
        idChap=b.getString("idChap");

//        arrUrlAnh= new ArrayList<>();
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406390_Conan-1108-00.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406316_Conan-1108-02.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406317_Conan-1108-03.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406320_Conan-1108-04.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406321_Conan-1108-05.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406323_Conan-1108-06.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406326_Conan-1108-07.jpg");
//        arrUrlAnh.add("https://cdn.kenhsinhvien.vn/data/2023/02/406327_Conan-1108-08.jpg");
//        soTrangDangDoc=1;
//        soTrang=arrUrlAnh.size();
    }
    private void anhXa(){
        imgAnh=findViewById(R.id.imgAnh);
        txvSoTrang=findViewById(R.id.txvSoTrang);

    }
    private void setUp(){
        //docTheoTrang(0);
    }
    private void setClick(){

    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    public void right(View view) {
        docTheoTrang(1);
    }
    private void docTheoTrang(int i){
        soTrangDangDoc=soTrangDangDoc+i;
        if(soTrangDangDoc==0){
            soTrangDangDoc=1;
        }
        if(soTrangDangDoc>soTrang){
            soTrangDangDoc=soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc+" / "+soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc-1)).into(imgAnh);
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {

        try {
            arrUrlAnh = new ArrayList<>();
            JSONArray arr= new JSONArray(data);
            for (int i=0;i<arr.length();i++){
                arrUrlAnh.add(arr.getString(i));
            }
            soTrangDangDoc=1;

            soTrang=arrUrlAnh.size();
            docTheoTrang(0);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {

    }
}