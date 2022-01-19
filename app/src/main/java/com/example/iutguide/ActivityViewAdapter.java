package com.example.iutguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ActivityViewAdapter extends BaseAdapter {

    String[] courseName;
    String[] title;
    String[] time;
    String[] date;
    String[] description;
    Context context;
    private LayoutInflater inflater;

    ActivityViewAdapter(Context context, String[] courseName, String[] title,String[] time,String[] date,String[] description){
        this.context = context;
        this.courseName = courseName;
        this.title = title;
        this.time = time;
        this.date = date;
        this.description = description;

    }

    @Override
    public int getCount() {
        return courseName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_view_layout,viewGroup,false);


        }

        TextView courseNames = (TextView) view.findViewById(R.id.courseName);
        TextView activityTitle = (TextView) view.findViewById(R.id.activityTitle);
        TextView activityTime = (TextView) view.findViewById(R.id.activityTime);
        TextView activityDate = (TextView) view.findViewById(R.id.activityDate);
        TextView activityDescription = (TextView) view.findViewById(R.id.activityDescription);

        courseNames.setText(courseName[i]);
        activityTitle.setText(title[i]);
        activityTime.setText(time[i]);
        activityDate.setText(date[i]);
        activityDescription.setText(description[i]);

        return view;
    }
}
