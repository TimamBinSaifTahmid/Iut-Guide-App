package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addCourse extends AppCompatActivity {
    Intent intent= getIntent();
    DatabaseReference databaseReference;
    DatabaseReference reference;
    private EditText addCourseET1;
    private EditText addCourseET2;
    private EditText addCourseET3;
    private Button addCourseb2;
    int cnt=0;
    int cnt2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        databaseReference= FirebaseDatabase.getInstance().getReference("Course");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           if(dataSnapshot.exists())
               cnt2= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        LogIn logIn=new LogIn();
        String SID= logIn.StudentId();
        reference=FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(SID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                    cnt=(int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        addCourseET1=findViewById(R.id.addCourseET1);
        addCourseET2=findViewById(R.id.addCourseET2);
        addCourseET3=findViewById(R.id.addCourseET3);
        addCourseb2=findViewById(R.id.addCourseb2);
       addCourseb2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               saveData();
           }
       });
    }
public void saveData(){
        String CName=addCourseET1.getText().toString().trim();
        String CId=addCourseET2.getText().toString().trim();
        String CCredit=addCourseET3.getText().toString().trim();
        String key=databaseReference.push().getKey();
        AddCourseFirebase addCourseFirebase=new AddCourseFirebase(CName,CId,CCredit);
        databaseReference.child(String.valueOf(cnt2+1)).setValue(addCourseFirebase);
        reference.child(String.valueOf(cnt+1)).setValue(addCourseFirebase);
    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
    addCourseET1.setText("");
    addCourseET2.setText("");
    addCourseET3.setText("");
}
}
