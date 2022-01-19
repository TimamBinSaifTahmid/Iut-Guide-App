package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activities extends AppCompatActivity {
    Intent intent= getIntent();

    private ListView activitiesListview;
    private DatabaseReference studentBatch;
    private DatabaseReference courses;
    private DatabaseReference assignment;
    private DatabaseReference quiz;
    private DatabaseReference presentation;

    private String currentMonth;
    private String currentDatte;
    private String currentDay;

    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String datee;

    private String dateCheck;
    private String dateCheckFinal;

    private String batchName;
    int cnt=0;

    int assignmentcount = 0, quizcount = 0, presentationcount = 0;

    int temp1=0;

    String courseList[];
    String title[] = new String[100];
    String date[] = new String[100];
    String time[] = new String[100];
    String description[] = new String[100];
    String finalCourseName[] = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        Calendar calendar = Calendar.getInstance();
        final String currentDate= DateFormat.getDateInstance().format(calendar.getTime());

        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        datee = dateFormat.format(calendar.getTime());


        final char month[] = new char[2];
        datee.getChars(0, 2, month, 0);
        currentMonth = String.valueOf(month);

        final char datte[] = new char[2];
        datee.getChars(3, 5, datte, 0);
        currentDatte = String.valueOf(datte);

        currentDay = currentMonth+currentDatte;



        LogIn logIn=new LogIn();
        String SID= logIn.StudentId();

        activitiesListview=(ListView) findViewById(R.id.activitiesList);
        final ActivityViewAdapter activityViewAdapter = new ActivityViewAdapter (Activities.this,finalCourseName,title,time,date,description);
        activitiesListview.setAdapter(activityViewAdapter);


        studentBatch= FirebaseDatabase.getInstance().getReference().child("Batch_Selected").child(SID);
        studentBatch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                batchName = dataSnapshot.getValue().toString();

                courses = FirebaseDatabase.getInstance().getReference().child("Batch_Course").child(batchName);
                courses.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            cnt = (int) dataSnapshot.getChildrenCount();
                            courseList = new String[cnt];
                            for(int i=1;i<=cnt;i++){
                                courseList[i-1] = dataSnapshot.child(String.valueOf(i)).getValue().toString();
                                Toast.makeText(getApplicationContext(),courseList[i-1],Toast.LENGTH_SHORT).show();

                                assignment = FirebaseDatabase.getInstance().getReference().child("Assignment").child(courseList[i-1]);
                                final int finalI = i;
                                assignment.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            assignmentcount = (int) dataSnapshot.getChildrenCount();
                                            for (int j=1;j<=assignmentcount;j++){

                                                dateCheck = dataSnapshot.child(String.valueOf(j)).child("task_date").getValue().toString();
                                                dateCheckFinal = dateCheck.substring(0,2)+dateCheck.substring(3,5);

                                                if(currentDay.compareTo(dateCheckFinal)<0){
                                                    finalCourseName[temp1] = courseList[finalI -1];
                                                    title[temp1] ="Title : "+ dataSnapshot.child(String.valueOf(j)).child("task_title").getValue().toString();
                                                    time[temp1] = "Time : "+dataSnapshot.child(String.valueOf(j)).child("task_time").getValue().toString();
                                                    date[temp1] = "Date : "+dataSnapshot.child(String.valueOf(j)).child("task_date").getValue().toString();
                                                    description[temp1] ="Description : " +dataSnapshot.child(String.valueOf(j)).child("task_Description").getValue().toString();
                                                    temp1++;
                                                }


                                            }
                                        }

                                        activityViewAdapter.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                quiz = FirebaseDatabase.getInstance().getReference().child("Quiz").child(courseList[i-1]);
                                quiz.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            quizcount = (int) dataSnapshot.getChildrenCount();
                                            for (int j=1;j<=quizcount;j++){

                                                dateCheck = dataSnapshot.child(String.valueOf(j)).child("task_date").getValue().toString();
                                                dateCheckFinal = dateCheck.substring(0,2)+dateCheck.substring(3,5);

                                                if(currentDay.compareTo(dateCheckFinal)<0) {
                                                    finalCourseName[temp1] = courseList[finalI -1];
                                                    title[temp1] = "Title : "+dataSnapshot.child(String.valueOf(j)).child("task_title").getValue().toString();
                                                    time[temp1] = "Time : "+dataSnapshot.child(String.valueOf(j)).child("task_time").getValue().toString();
                                                    date[temp1] = "Date : "+dataSnapshot.child(String.valueOf(j)).child("task_date").getValue().toString();
                                                    description[temp1] = "description : "+dataSnapshot.child(String.valueOf(j)).child("task_Description").getValue().toString();

                                                    temp1++;
                                                }
                                        }

                                    }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                presentation = FirebaseDatabase.getInstance().getReference().child("Presentation").child(courseList[i-1]);
                                presentation.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            presentationcount = (int) dataSnapshot.getChildrenCount();
                                            for (int j=1;j<=presentationcount;j++){

                                                dateCheck = dataSnapshot.child(String.valueOf(j)).child("task_date").getValue().toString();
                                                dateCheckFinal = dateCheck.substring(0,2)+dateCheck.substring(3,5);

                                                if(currentDay.compareTo(dateCheckFinal)<0) {
                                                    finalCourseName[temp1] = courseList[finalI -1];
                                                    title[temp1] = "Title : "+dataSnapshot.child(String.valueOf(j)).child("task_title").getValue().toString();
                                                    time[temp1] = "Time"+dataSnapshot.child(String.valueOf(j)).child("task_time").getValue().toString();
                                                    date[temp1] = "Date : "+dataSnapshot.child(String.valueOf(j)).child("task_date").getValue().toString();
                                                    description[temp1] = "Description : "+dataSnapshot.child(String.valueOf(j)).child("task_Description").getValue().toString();

                                                    temp1++;
                                                }
                                        }

                                    }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
