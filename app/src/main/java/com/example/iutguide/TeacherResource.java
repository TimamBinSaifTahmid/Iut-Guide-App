package com.example.iutguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherResource extends AppCompatActivity {
    Intent intent = getIntent();
    EditText resourceTitle;
    EditText resourceLink;
    Button addResourceButton;
    String courseId, linkRes, titleRes;
    private DatabaseReference courseIDreference;
    private DatabaseReference resourceSend;

    int cnt = 0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_resource);

        LogIn logIn = new LogIn();
        String TeacherID = logIn.StudentId();
        Course course = new Course();
        final int position = course.getposition();



        courseIDreference = FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(TeacherID).child(String.valueOf(position));
        courseIDreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseId = dataSnapshot.child("courseId").getValue().toString();

                resourceSend = FirebaseDatabase.getInstance().getReference().child("Resource").child(courseId);
                resourceSend.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.exists()){
                           cnt = (int) dataSnapshot.getChildrenCount();
                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        resourceTitle= findViewById(R.id.titleOfResource);
        resourceLink= findViewById(R.id.linkResource);
        addResourceButton= findViewById(R.id.addResourceButton);

        addResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleRes = resourceTitle.getText().toString();
                linkRes = resourceLink.getText().toString();

                resourceSend.child(String.valueOf((cnt+1))).child("resourceTitle").setValue(titleRes);
                resourceSend.child(String.valueOf((cnt+1))).child("resourceLink").setValue(linkRes);
            }
        });
    }
}