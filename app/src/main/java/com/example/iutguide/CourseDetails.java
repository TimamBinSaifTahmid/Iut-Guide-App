package com.example.iutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

public class CourseDetails extends AppCompatActivity {
    Intent intent= getIntent();
    Button attendence;
    Button resourses;
    Button tasks;
    Button percentage;
    Button AddClass;
    StudentAccount studentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        studentAccount = new StudentAccount();
        percentage = (Button) findViewById(R.id.PercentageButton);
        attendence = (Button) findViewById(R.id.AttendenceButton);
        resourses = (Button) findViewById(R.id.ResourcesButton);
        tasks = (Button) findViewById(R.id.TasksButton);
        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetails.this, TasksTeacher.class);
                startActivity(intent);
            }
        });
        final Student_Course student_course = new Student_Course();


        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student_course.verify() == 1) {
                    Intent intent = new Intent(CourseDetails.this, StudentAttendence.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CourseDetails.this, Attendence.class);
                    startActivity(intent);
                }
            }
        });
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student_course.verify() == 1) {
                    Intent intent = new Intent(CourseDetails.this, StudentPercentage.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CourseDetails.this, TeacherPercentage.class);
                    startActivity(intent);
                }
            }

        });

        resourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student_course.verify() == 1) {
                    Intent intent = new Intent(CourseDetails.this, StudentResource.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CourseDetails.this, TeacherResource.class);
                    startActivity(intent);
                }
            }
        });


            AddClass = findViewById(R.id.AddClassButton);
        AddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetails.this, AddClass.class);
                startActivity(intent);
            }
        });

    }
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),String.valueOf(studentAccount.getStudentVerify()),Toast.LENGTH_SHORT).show();
        if (studentAccount.getStudentVerify() == 1) {
            AddClass.setVisibility(View.INVISIBLE);
           tasks.setVisibility(View.INVISIBLE);
        }
    }
}
