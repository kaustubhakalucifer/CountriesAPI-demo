package com.example.countriesapi_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<itemModel> arrayList;
    public CustomAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }
        TextView countryName, capital;
        countryName = (TextView) convertView.findViewById(R.id.countryName);
        capital = (TextView) convertView.findViewById(R.id.capital);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.flag);
        Glide.with(context).load("https://www.countryflags.io/"+arrayList.get(position).getAlpha2code()+"/flat/64.png").fitCenter().into(imageView);
        countryName.setText(arrayList.get(position).getCountryName());
        capital.setText(arrayList.get(position).getCapital());
        return convertView;
    }
}
