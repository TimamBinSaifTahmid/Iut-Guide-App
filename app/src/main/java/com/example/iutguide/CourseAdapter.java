package com.example.iutguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<AddCourseFirebase> {
   // Intent intent= getIntent();
    private Activity context;

    private List<AddCourseFirebase>addCourseFirebaseList;

    public CourseAdapter(Activity context,List<AddCourseFirebase> addCourseFirebaseList) {
        super(context, R.layout.sample_c_layout,addCourseFirebaseList);
        this.context = context;
        this.addCourseFirebaseList=addCourseFirebaseList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
       View view= layoutInflater.inflate(R.layout.sample_c_layout,null,true);
        AddCourseFirebase add_c_Firebase=addCourseFirebaseList.get(position);

        TextView t1=view.findViewById(R.id.sampltT1);
        TextView t2=view.findViewById(R.id.sampltT2);
        TextView t3=view.findViewById(R.id.sampltT3);
        t1.setText(add_c_Firebase.getCourseName());
        t2.setText(add_c_Firebase.getCourseId());
        t3.setText(add_c_Firebase.getCourseCredit());
        return view;
    }
}
