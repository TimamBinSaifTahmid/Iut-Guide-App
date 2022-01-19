package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TasksTeacher extends AppCompatActivity {
    Intent intent=getIntent();
    private RadioButton quiz;
    private RadioButton assignment;
    private RadioButton presentation;
    EditText taskTitle;
    EditText taskTime;
    EditText taskDate;
    EditText taskDescription;
    Button taskAddButton;
    DatabaseReference courseInfo;
    String courseName;
    String taskSpinnerString;
    int cnt = 0;
    int qui=0,ass=0,pre=0;
    DatabaseReference sendTasks;

    String[] task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_teacher);
        quiz = findViewById(R.id.quiz);
        assignment = findViewById(R.id.assignment);
        presentation = findViewById(R.id.presentation);
        taskTitle=(EditText)findViewById(R.id.taskTitle);
        taskTime=(EditText)findViewById(R.id.taskTime);
        taskDate=(EditText)findViewById(R.id.taskDate);
        taskDescription=(EditText)findViewById(R.id.taskDescription);
        taskAddButton=(Button)findViewById(R.id.taskAddButton);
        task=getResources().getStringArray(R.array.Task);

        LogIn login = new LogIn();
        String teacherid = login.StudentId();

        Course course = new Course();
        int position = course.getposition();

        courseInfo = FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(teacherid).child(String.valueOf(position));
        sendTasks = FirebaseDatabase.getInstance().getReference();

        courseInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseName = dataSnapshot.child("courseId").getValue().toString();

//                Toast.makeText(getApplicationContext(),taskSpinnerString,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSpinnerString = "Quiz";
                sendTasks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(taskSpinnerString).child(courseName).exists()){
                            cnt = (int) dataSnapshot.child(taskSpinnerString).child(courseName).getChildrenCount();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSpinnerString = "Assignment";
                sendTasks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(taskSpinnerString).child(courseName).exists()){
                            cnt = (int) dataSnapshot.child(taskSpinnerString).child(courseName).getChildrenCount();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        presentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSpinnerString = "Presentation";
                sendTasks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(taskSpinnerString).child(courseName).exists()){
                            cnt = (int) dataSnapshot.child(taskSpinnerString).child(courseName).getChildrenCount();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        


        taskAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitleString=taskTitle.getText().toString();
                String taskTimeString=taskTime.getText().toString();
                String taskDateString=taskDate.getText().toString();
                String taskDescriptionString=taskDescription.getText().toString();

                sendTasks.child(taskSpinnerString).child(courseName).child(String.valueOf((cnt+1))).child("task_date").setValue(taskDateString);
                sendTasks.child(taskSpinnerString).child(courseName).child(String.valueOf((cnt+1))).child("task_time").setValue(taskTimeString);
                sendTasks.child(taskSpinnerString).child(courseName).child(String.valueOf((cnt+1))).child("task_Description").setValue(taskDescriptionString);
                sendTasks.child(taskSpinnerString).child(courseName).child(String.valueOf((cnt+1))).child("task_title").setValue(taskTitleString);

            }
        });
    }
}
