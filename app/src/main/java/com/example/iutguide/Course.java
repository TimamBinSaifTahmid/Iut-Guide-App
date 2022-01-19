package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class  Course extends AppCompatActivity {
    Intent intent= getIntent();
   private ListView listView;
   Button CourseB1;
   private List<AddCourseFirebase> addCourseFirebaseList;
   private CourseAdapter courseAdapter;
   private static int verify;
   DatabaseReference mDatabase;
   int cnt=0;
  static int position1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        LogIn logIn=new LogIn();
        final CourseDetails courseDetails=new CourseDetails();
        String str;
        str=logIn.StudentId();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(str);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           if(dataSnapshot.exists())
                cnt=(int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addCourseFirebaseList=new ArrayList<>();

        courseAdapter=new CourseAdapter(Course.this,addCourseFirebaseList);
        listView=findViewById(R.id.courseL1);
        CourseB1=findViewById(R.id.CourseB);
        CourseB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Course.this,addCourse.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               for(int i=0;i<cnt;i++){
                if(position==i) {
                    position1=(i+1);
                    Student_Course.verify=0;
                    Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(), CourseDetails.class);
                    startActivity(intent3);
                     break;
                }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                addCourseFirebaseList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    AddCourseFirebase addCourseFirebase=dataSnapshot1.getValue(AddCourseFirebase.class);
                    addCourseFirebaseList.add(addCourseFirebase);
                }

                listView.setAdapter(courseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
    int getposition(){
        return position1;
    }
    int verify(){
        return verify;
    }
}
