package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class StudentAttendence extends AppCompatActivity {
  public static TextView studentAttendenceT1;
Button studentAttendeceB1;
private ZXingScannerView scannerView;
private static final int RequestCode=1;
DatabaseReference reference;
int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendence);
        studentAttendeceB1=(Button) findViewById(R.id.studentAttendenceB1);
        studentAttendenceT1=(TextView) findViewById(R.id.studentAttendenceT1);

           studentAttendeceB1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   verifypermission();
                   if(num==1) {
                       startActivity(new Intent(getApplicationContext(), Scanner.class));
                   }
               }
           });
       }


    private void verifypermission(){
        String [] permission ={Manifest.permission.CAMERA};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),permission[0])== PackageManager.PERMISSION_GRANTED){
            num=1;
        }
        else {
            ActivityCompat.requestPermissions(StudentAttendence.this,permission,RequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifypermission();
    }
}
