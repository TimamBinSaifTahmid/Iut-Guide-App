package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

  public static String qrCode;
  String batchName;
  String courseName;
  int position;
  int cnt=0;
  int cnt3=0;
  String date;
  String studentId;
  DatabaseReference reference1;
  DatabaseReference reference2;
  DatabaseReference reference3;
  DatabaseReference reference4;
  DatabaseReference reference5;
    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView=new ZXingScannerView(Scanner.this);
        setContentView(ScannerView);

           LogIn logIn=new LogIn();
           studentId=logIn.StudentId();
        Student_Course student_course=new Student_Course();
        position=(student_course.position());
        Student_Batch student_batch=new Student_Batch();
        Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),studentId,Toast.LENGTH_SHORT).show();
           reference4=FirebaseDatabase.getInstance().getReference().child("Batch_Selected").child(studentId);
           reference4.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   batchName=dataSnapshot.getValue().toString();
                   Toast.makeText(getApplicationContext(),batchName,Toast.LENGTH_SHORT).show();
                   reference1=FirebaseDatabase.getInstance().getReference().child("Batch_Course").child(batchName).child(String.valueOf(position));
                   reference1.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           courseName=dataSnapshot.getValue().toString();
                           Toast.makeText(getApplicationContext(),courseName,Toast.LENGTH_SHORT).show();
                           reference2=FirebaseDatabase.getInstance().getReference().child("Course_Date").child(courseName).child(batchName);
                           reference2.addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                   if(dataSnapshot.exists()){
                                       cnt=(int)dataSnapshot.getChildrenCount();
                                       date=dataSnapshot.child(String.valueOf(cnt)).getValue().toString();
                                       Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
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



    }

    @Override
    public void handleResult(Result result) {
        qrCode=result.getText();
         if(!qrCode.isEmpty()){
             reference5=FirebaseDatabase.getInstance().getReference().child("Attendence_QrCode").child(courseName).child(batchName).child(date);
             reference5.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String str= dataSnapshot.getValue().toString();
                    if(str.compareTo(qrCode)==0){
                        reference3=FirebaseDatabase.getInstance().getReference().child("Attendence").child(courseName).child(batchName).child(date).child(studentId);
                        reference3.setValue("p");
                        Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });
        }
        StudentAttendence.studentAttendenceT1.setText(result.getText());
         onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(Scanner.this);
        ScannerView.startCamera();
    }
    String getQrCode(){
        return qrCode;
    }
}
