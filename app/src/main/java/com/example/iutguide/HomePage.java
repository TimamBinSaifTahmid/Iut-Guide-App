package com.example.iutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button teacher;
    private Button student;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        teacher = (Button) findViewById(R.id.teacher);
        student = (Button) findViewById(R.id.student);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);


        LogIn login1=new LogIn();
        if(login1.checkbox()!=1) {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomePage.this, LogIn.class);
                    startActivity(intent);
                }
            });

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomePage.this, SignUp.class);
                    startActivity(intent);
                }
            });
        }
    }
}