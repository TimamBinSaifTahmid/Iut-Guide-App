package com.example.iutguide;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentCourseAdapter extends ArrayAdapter<StudentCourseClass> {

    private Activity contex;
    private List<StudentCourseClass> studentCourseList;

    public StudentCourseAdapter(Activity context,List<StudentCourseClass> studentCourseList) {
        super(context, R.layout.studentcoursesample, studentCourseList);
        this.contex = contex;
        this.studentCourseList = studentCourseList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater=contex.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.studentcoursesample,null,true);
        StudentCourseClass studentCourseClass=studentCourseList.get(position);
        TextView t1=view.findViewById(R.id.StudentCourseST1);
        t1.setText(studentCourseClass.getCourseName());
        return view;
    }
}
