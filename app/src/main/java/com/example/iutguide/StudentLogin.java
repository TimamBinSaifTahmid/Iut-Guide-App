package com.example.iutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentLogin extends AppCompatActivity {
    Intent intent= getIntent();
    Button signout2;
    Button student;
    FirebaseAuth sout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        signout2=(Button)findViewById(R.id.signout2);
        student=(Button)findViewById(R.id.student);
        sout=FirebaseAuth.getInstance();
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentLogin.this,StudentAccount.class);
                startActivity(intent);
            }
        });
        signout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent=new Intent(StudentLogin.this,HomePage.class);
                startActivity(intent);
            }
        });


    }
}
