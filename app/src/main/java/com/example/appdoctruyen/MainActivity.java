package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.appdoctruyen.adapter.TruyenTranhAdapter;
import com.example.appdoctruyen.api.ApiLayTruyen;
import com.example.appdoctruyen.interfaces.LayTruyenVe;
import com.example.appdoctruyen.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
GridView gvDSTruyen;
TruyenTranhAdapter adapter;
ArrayList<TruyenTranh> truyenTranhArrayList;
EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    private void init(){
        truyenTranhArrayList=new ArrayList<>();

        adapter= new TruyenTranhAdapter(this,0,truyenTranhArrayList);

    }
    private void anhXa(){
        gvDSTruyen= findViewById(R.id.gvDSTruyen);
        edtTimKiem= findViewById(R.id.edtTimKiem);

    }
    private void setUp(){
        gvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s= edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
        gvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TruyenTranh truyenTranh= truyenTranhArrayList.get(i);
                Bundle b =new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent= new Intent(MainActivity.this,ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Dang lay ve", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ketThuc(String data) {
        try{
            truyenTranhArrayList.clear();
            JSONArray arr= new JSONArray(data);
            for(int i=0;i<arr.length();i++){
                JSONObject o= arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter= new TruyenTranhAdapter(this,0,truyenTranhArrayList);
            gvDSTruyen.setAdapter(adapter);

        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();

    }

    public void update(View view) {
        new ApiLayTruyen(this).execute();



    }
}