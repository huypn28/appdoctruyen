package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.object.ChapTruyen;

import java.util.ArrayList;
import java.util.List;

public class ChapTruyenAdapter extends ArrayAdapter<ChapTruyen> {
    private Context ct;
    private ArrayList<ChapTruyen> arr;
    public ChapTruyenAdapter(Context context, int resource, List<ChapTruyen> objects){
        super(context,resource,objects);
        this.ct= context;
        this.arr= new ArrayList<>(objects);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater=(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_chap_truyen,null);
        }
        if (arr.size()>0){
            TextView txvTenChaps,txvNgay;
            txvTenChaps=convertView.findViewById(R.id.txvTenChaps);
            txvNgay=convertView.findViewById(R.id.txvNgay);

            ChapTruyen chapTruyen= arr.get(position);
            txvNgay.setText(chapTruyen.getNgayDang());
            txvTenChaps.setText(chapTruyen.getTenChap());
        }
        return convertView;
    }
}
