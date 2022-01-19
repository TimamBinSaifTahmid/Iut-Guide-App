package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teacheraccount extends AppCompatActivity {
    Intent intent= getIntent();
    private Button Courses;
    private Button TeacherEvents;
    Button teacherAccountB4;
    Button upcomingevent;
    static int teacherverify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacheraccount);


        teacherAccountB4=(Button)findViewById(R.id.teacherAccountB4);
        teacherAccountB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(teacheraccount.this,QrCodeGenerator.class);
                startActivity(intent);
            }
        });
        TeacherEvents=(Button)findViewById(R.id.teacherevents);
        TeacherEvents.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(teacheraccount.this,Events.class);
                startActivity(intent);
            }
        });

        Courses=(Button)findViewById(R.id.courses);
        Courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentAccount.studentverify=0;
                teacherverify=1;
                Intent intent=new Intent(teacheraccount.this,Course.class);
                startActivity(intent);
            }
        });

        upcomingevent=(Button)findViewById(R.id.teacherAccountB5);
        upcomingevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(teacheraccount.this,Upcoming_teacher_activities.class);
                startActivity(intent);
            }
        });

    }
    int getTeacherverify(){
        return teacherverify;
    }
}
