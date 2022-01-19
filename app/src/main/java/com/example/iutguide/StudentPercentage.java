package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.TreeMap;

public class StudentPercentage extends AppCompatActivity {
    Intent intent=getIntent();
    Button reload;
    TextView percentage;
String studentId;
String batchName;
String courseName;
int position;
double present=0;
int cnt=0;
double totalclass;
String date[];
DatabaseReference reference1;
DatabaseReference reference2;
DatabaseReference reference3;
DatabaseReference reference4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_percentage);
        reload=(Button)findViewById(R.id.StudentparcentageB1);
        percentage=(TextView)findViewById(R.id.StudentpercentageT1);
        LogIn logIn=new LogIn();
        studentId=logIn.StudentId();
        Student_Course student_course=new Student_Course();
        position=student_course.position();
        reference1= FirebaseDatabase.getInstance().getReference().child("Batch_Selected").child(studentId);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                batchName=dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(), batchName, Toast.LENGTH_SHORT).show();
                reference2=FirebaseDatabase.getInstance().getReference().child("Batch_Course").child(batchName).child(String.valueOf(position));
                reference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        courseName=dataSnapshot.getValue().toString();
                        Toast.makeText(getApplicationContext(), courseName, Toast.LENGTH_SHORT).show();
                        reference3=FirebaseDatabase.getInstance().getReference().child("Course_Date").child(courseName).child(batchName);
                        reference3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    cnt=(int)dataSnapshot.getChildrenCount();
                                    totalclass=cnt;
                                    date=new String[cnt];
                                    for(int i=1;i<=cnt;i++){
                                        date[i-1]=dataSnapshot.child(String.valueOf(i)).getValue().toString();
                                        Toast.makeText(getApplicationContext(),date[i-1], Toast.LENGTH_SHORT).show();
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
reload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        reference4=FirebaseDatabase.getInstance().getReference().child("Attendence").child(courseName).child(batchName);
        for(int i=1;i<=cnt;i++){
            final int finalI = i;
            reference4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   String temp= dataSnapshot.child(date[finalI -1]).child(studentId).getValue().toString();
                    Toast.makeText(getApplicationContext(),temp, Toast.LENGTH_SHORT).show();
                   if(temp.compareTo("p")==0){
                       present++;
                   }
                   if(finalI==totalclass){
                       double round=(present/totalclass)*100;
                       round=Math.round(round*100.0)/100.0;
                       Toast.makeText(getApplicationContext(),String.valueOf(round+"%"), Toast.LENGTH_SHORT).show();
                       percentage.setText(String.valueOf(round+"%"));

                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });

        }
        reload.setVisibility(View.INVISIBLE);

    }
});

    }
}
