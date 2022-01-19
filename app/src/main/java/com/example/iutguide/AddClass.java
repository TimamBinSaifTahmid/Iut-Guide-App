package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClass extends AppCompatActivity {

    private RadioButton monday;
    private RadioButton tuesday;
    private RadioButton wedday;
    private RadioButton thuday;
    private RadioButton friday;
    private EditText batchName;
    private EditText starting;
    private EditText ending;
    private Button addClassButton;
    private String courseName;

    int mon=0,tue=0,wed=0,thu=0,fri=0;

    private DatabaseReference sendClassInfo;
    private DatabaseReference courseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wedday = findViewById(R.id.weddey);
        thuday = findViewById(R.id.thuday);
        friday = findViewById(R.id.friday);

        LogIn login = new LogIn();
        String teacherid = login.StudentId();

        Course course = new Course();
        int position = course.getposition();

        courseInfo = FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(teacherid).child(String.valueOf(position));
        courseInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseName = dataSnapshot.child("courseId").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        batchName = findViewById(R.id.addBatch);
        starting = findViewById(R.id.startsat);
        ending = findViewById(R.id.endsat);
        addClassButton = findViewById(R.id.addClassButton);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mon=1;
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tue = 1;
            }
        });
        wedday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wed = 1;
            }
        });
        thuday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thu = 1;
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fri = 1;
            }
        });

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String batchname = batchName.getText().toString();
                String start = starting.getText().toString();
                String end = ending.getText().toString();
int cnt=0;
                sendClassInfo = FirebaseDatabase.getInstance().getReference().child("Classes").child(batchname).child(courseName);
                if(mon == 1){
                    cnt++;
                    sendClassInfo.child(String.valueOf(cnt)).child("day").setValue("Mon");
                    sendClassInfo.child(String.valueOf(cnt)).child("class starts at").setValue(start);
                    sendClassInfo.child(String.valueOf(cnt)).child("class ends at").setValue(end);
                }
                if(tue == 1){
                    cnt++;
                    sendClassInfo.child(String.valueOf(cnt)).child("day").setValue("Tue");
                    sendClassInfo.child(String.valueOf(cnt)).child("class starts at").setValue(start);
                    sendClassInfo.child(String.valueOf(cnt)).child("class ends at").setValue(end);
                }
                if(wed == 1){
                    cnt++;
                    sendClassInfo.child(String.valueOf(cnt)).child("day").setValue("Wed");
                    sendClassInfo.child(String.valueOf(cnt)).child("class starts at").setValue(start);
                    sendClassInfo.child(String.valueOf(cnt)).child("class ends at").setValue(end);
                }
                if(thu == 1){
                    cnt++;
                    sendClassInfo.child(String.valueOf(cnt)).child("day").setValue("Thu");
                    sendClassInfo.child(String.valueOf(cnt)).child("class starts at").setValue(start);
                    sendClassInfo.child(String.valueOf(cnt)).child("class ends at").setValue(end);
                }
                if(fri == 1){
                    cnt++;
                    sendClassInfo.child(String.valueOf(cnt)).child("day").setValue("Fri");
                    sendClassInfo.child(String.valueOf(cnt)).child("class starts at").setValue(start);
                    sendClassInfo.child(String.valueOf(cnt)).child("class ends at").setValue(end);
                }




            }
        });
    }
}
