package com.example.iutguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] generalized_string;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, String[] generalized_string) {
        this.context = context;
        this.generalized_string = generalized_string;
    }

    @Override
    public int getCount() {
        return generalized_string.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
           layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=layoutInflater.inflate(R.layout.sample_layout,parent,false);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.listText);
        textView.setText(generalized_string[position]);
        return convertView;
    }
}
