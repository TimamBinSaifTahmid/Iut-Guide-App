package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student_Batch extends AppCompatActivity {
    Intent intent = getIntent();
   private EditText studentBatchE1;
   private Button studentBatchB1;
   static String batchName;
   String  str;
   String SID;
   String t1;
   DatabaseReference reference;
   DatabaseReference reference2;
   DatabaseReference reference3;
   DatabaseReference reference4;
   DatabaseReference reference5;
    int cnt=0;
    int cnt2=0;
    int cnt3=0;
    int cnt5=0;
    String s1[];
    int num[];
  int databasePosition;
  int exist=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__batch);
        LogIn login=new LogIn();
        SID=login.StudentId();
        databasePosition=login.databasePosition();
        studentBatchE1=findViewById(R.id.StudentBatchE1);
     studentBatchB1=findViewById(R.id.StudentBatchb1);
 reference4=FirebaseDatabase.getInstance().getReference().child("Course_Student");
reference5=FirebaseDatabase.getInstance().getReference().child("Batch_Selected").child(SID);
reference5.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
       if(dataSnapshot.exists()){
           batchName=dataSnapshot.getValue().toString();
           Intent intent=new Intent(Student_Batch.this,Student_Course.class);
           startActivity(intent);
       }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


      studentBatchE1.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               str=studentBatchE1.getText().toString();
             if(str.length()==5){
                 Toast.makeText(getApplicationContext(),"Batch added",Toast.LENGTH_SHORT).show();
                 batchName=str;
                 reference3=FirebaseDatabase.getInstance().getReference().child("Batch_Course").child(batchName);
                 reference3.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         if(dataSnapshot.exists())
                             cnt3=(int)dataSnapshot.getChildrenCount();
                         s1= new String [cnt3];
                         num=new int [cnt3];
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });

                 reference3.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                         for(int i=1;i<=cnt3;i++){
                             cnt2=0;
                             s1[i-1]=dataSnapshot.child(String.valueOf(i)).getValue().toString();
                             String str=s1[i-1];
                             Toast.makeText(getApplicationContext(),s1[i-1],Toast.LENGTH_SHORT).show();
                             reference2=FirebaseDatabase.getInstance().getReference().child("Course_Student").child(str);
                             final int finalI = i;
                             final int fianalcnt=cnt2;
                             reference2.addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  if(dataSnapshot.exists()) {
                                      cnt2 = (int) dataSnapshot.getChildrenCount();
                                      num[finalI-1]=(cnt2+1);
                                      Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                                  }
                                  else {
                                      num[finalI - 1] = (cnt2 + 1);
                                      Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                                  }

                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError databaseError) {

                                 }
                             });

                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });

                 reference=FirebaseDatabase.getInstance().getReference().child("Batch_Student").child(batchName);
                 reference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         if(dataSnapshot.exists()) {
                             cnt = (int) dataSnapshot.getChildrenCount();
                             t1= String.valueOf(cnt+1);


                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });

             }

          }

          @Override
          public void afterTextChanged(Editable editable) {

          }
      });



      studentBatchB1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              for(int i=0;i<cnt3;i++){
                  reference4.child(s1[i]).child(String.valueOf(num[i])).setValue(SID);
              }

              reference.child(String.valueOf(cnt+1)).setValue(SID);
              reference5.setValue(str);
              Intent intent2=new Intent(Student_Batch.this,Student_Course.class);
              startActivity(intent2);


          }
      });


    }
    String getBatchName(){
        return batchName;
    }

}