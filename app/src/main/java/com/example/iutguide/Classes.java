package com.example.iutguide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;


public class Classes extends AppCompatActivity {
    Intent intent = getIntent();
    private ListView classList;
    private DatabaseReference courseData;
    private DatabaseReference day;
    private DatabaseReference batchData;
    private String studentID;
    private String batchName;
    private String[] courseName;
    private int cnt = 0;
    private int cnt2 = 0;
    private String dayData;
    String[] courseInfo = new String[100];
    String[] classStarting = new String[100];
    String[] classEnding = new String[100];
    int size = 0;

    private String[] temp1, temp2, temp3;

    DatabaseReference classes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String current = currentDate;

        final char toda[] = new char[3];
        currentDate.getChars(0, 3, toda, 0);

        final String today = String.valueOf(toda);


        LogIn login = new LogIn();
        studentID = login.StudentId();

        final ClassViewAdapter classViewAdapter = new ClassViewAdapter(Classes.this, courseInfo, classStarting, classEnding);
        classList = (ListView) findViewById(R.id.classList);
        classList.setAdapter(classViewAdapter);


        batchData = FirebaseDatabase.getInstance().getReference().child("Batch_Selected").child(studentID);

        batchData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                batchName = dataSnapshot.getValue().toString();

                courseData = FirebaseDatabase.getInstance().getReference().child("Batch_Course").child(batchName);
                courseData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            cnt = (int) dataSnapshot.getChildrenCount();
                            courseName = new String[cnt];

                            for (int i = 1; i <= cnt; i++) {
                                courseName[i - 1] = dataSnapshot.child(String.valueOf(i)).getValue().toString();
                                // Toast.makeText(getApplicationContext(),courseName[i-1],Toast.LENGTH_SHORT).show();

                            }

                            for (int i = 1; i <= cnt; i++) {

                                classes = FirebaseDatabase.getInstance().getReference().child("Classes").child(batchName).child(courseName[i - 1]);
                                final int finalI = i;
                                classes.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            cnt2 = (int) dataSnapshot.getChildrenCount();
                                            for (int j = 1; j <= cnt2; j++) {
                                                dayData = dataSnapshot.child(String.valueOf(j)).child("day").getValue().toString();
                                                final String temp = dayData;


                                                if (today.compareTo(temp) == 0) {
                                                    courseInfo[size] = courseName[finalI - 1];
                                                    classStarting[size] = "Class Starting Time : " + dataSnapshot.child(String.valueOf(j)).child("class starts at").getValue().toString();
                                                    classEnding[size] = "Class Endng Time : " + dataSnapshot.child(String.valueOf(j)).child("class ends at").getValue().toString();

                                                    size++;

                                                }
                                            }

                                        }

                                        classViewAdapter.notifyDataSetChanged();

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
