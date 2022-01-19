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

public class StudentAccount extends AppCompatActivity {
    Intent intent= getIntent();
    private Button studentCourse;
    private Button classes;
    private Button activity;
    private Button library;
    private Button event;
    public String SID;
    static int studentverify;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_account);
        reference= FirebaseDatabase.getInstance().getReference().child("Batch_Selected");
        studentCourse=findViewById(R.id.Studentb6);
        studentCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacheraccount.teacherverify=0;
                studentverify=1;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        LogIn login=new LogIn();
                        SID=login.StudentId();
                        if(dataSnapshot.child(SID).exists()){
                            Intent intent=new Intent(StudentAccount.this,Student_Course.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent=new Intent(StudentAccount.this,Student_Batch.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        classes=(Button)findViewById(R.id.classes);
        activity=(Button)findViewById(R.id.activities);
        library=(Button)findViewById(R.id.library);
        event=(Button)findViewById(R.id.event);
        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentAccount.this,Classes.class);
                startActivity(intent);
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentAccount.this,Activities.class);
                startActivity(intent);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentAccount.this,Library.class);
                startActivity(intent);
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentAccount.this,Events.class);
                startActivity(intent);
            }
        });

    }
    int getStudentVerify(){
        return studentverify;
    }

}
