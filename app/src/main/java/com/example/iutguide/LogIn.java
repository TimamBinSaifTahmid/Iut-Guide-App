package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    Intent intent = getIntent();
    String email;
    String str;
    int cnt1=0,cnt2=0;
    private Button login;
    private Button signup;
    private EditText name;
    private EditText password;
    private CheckBox remember_me;
    protected static String SID;
    private FirebaseAuth mAuth;
    DatabaseReference studentId;
    DatabaseReference teacherId;
    protected static int num;
    static int databasePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        studentId= FirebaseDatabase.getInstance().getReference("Student_Id_Email");
        studentId.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    cnt1= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        teacherId=FirebaseDatabase.getInstance().getReference("Teacher_Id_Email");
        teacherId.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    cnt2= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mAuth = FirebaseAuth.getInstance();
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        name=(EditText) findViewById(R.id.name);
        password=(EditText) findViewById(R.id.password);
        remember_me=(CheckBox)findViewById(R.id.rememberme);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginApproval();

            }
        });
        remember_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=1;
            }
        });




    }



    private void loginApproval() {
        email =name.getText().toString();
        String password1 = password.getText().toString();
        if (email.length()!=0 && password1.length()!=0) {
            mAuth.signInWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        SignUp singUp1=new SignUp();
                        int temp1=singUp1.getCheck1();
                        int temp2= singUp1.getCheck2();

                        studentId.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (int i = 1; i <= cnt1; i++) {
                                    String temp = dataSnapshot.child(String.valueOf(i)).child("EMAIL").getValue().toString();
                                    if (email.compareTo(temp) == 0) {
                                        databasePosition=i;
                                        SID = dataSnapshot.child(String.valueOf(i)).child("ID").getValue().toString();
                                        Toast.makeText(getApplicationContext(),SID, Toast.LENGTH_SHORT).show();
                                        Intent intent3=new Intent(LogIn.this,StudentAccount.class);
                                        startActivity(intent3);
                                        break;

                                    }
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





                        teacherId.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(int i=1;i<=cnt2;i++) {
                                    String temp = dataSnapshot.child(String.valueOf(i)).child("EMAIL").getValue().toString();
                                    if (email.compareTo(temp) == 0) {
                                        databasePosition=i;
                                        SID = dataSnapshot.child(String.valueOf(i)).child("ID").getValue().toString();
                                        Toast.makeText(getApplicationContext(),SID, Toast.LENGTH_SHORT).show();
                                        Intent intent4=new Intent(LogIn.this,teacheraccount.class);
                                        startActivity(intent4);
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {


                            }
                        });



                    }

                }



            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
    int checkbox(){
        return num;
    }
    String StudentId(){
        return SID;
    }
    int databasePosition(){
        return databasePosition;
    }
}