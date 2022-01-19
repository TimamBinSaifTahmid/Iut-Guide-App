package com.example.iutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherLogin extends AppCompatActivity {
    Intent intent= getIntent();
    Button signout1;
    Button Teacher;
    FirebaseAuth sOUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        signout1=(Button)findViewById(R.id.signout1);
        Teacher=(Button)findViewById(R.id.teacher);
        sOUT=FirebaseAuth.getInstance();

        Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherLogin.this,teacheraccount.class);
                startActivity(intent);
            }
        });

        signout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent=new Intent(TeacherLogin.this,HomePage.class);
                startActivity(intent);
            }
        });
    }
}
