package com.example.iutguide;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Attendence extends AppCompatActivity {
    Intent intent = getIntent();
    private static final String TAG = "Attendence";
    private TextView mDisplayDate;
    private EditText attendenceBatchSelect;
    DatabaseReference reference;
    DatabaseReference reference2;
    DatabaseReference reference3;
    DatabaseReference reference4;
    DatabaseReference reference5;
    String cDate;
    int batchCount = 0;
    String courseId;
    int cnt = 0;
    int cnt4 = 1;
    String qrCode;
    String studentlist[];
    String str,batchName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        LogIn logIn = new LogIn();
        final String ID = logIn.StudentId();
        Course course = new Course();
        final int position = course.getposition();
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        qrCode = qrCodeGenerator.getQrCode();
        attendenceBatchSelect=findViewById(R.id.attendenceBatchSelect);
        attendenceBatchSelect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                str=attendenceBatchSelect.getText().toString();
                if(str.length()==5) {
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    batchName = str;
                    reference2 = FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(ID).child(String.valueOf(position));
                    reference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            courseId = dataSnapshot.child("courseId").getValue().toString();
                            reference4 = FirebaseDatabase.getInstance().getReference().child("Course_Date").child(courseId).child(batchName);
                            reference4.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.child(batchName).exists()) {
                                        cnt4 = (int) dataSnapshot.getChildrenCount();
                                        cnt4++;

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), courseId, Toast.LENGTH_SHORT).show();
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

        reference3 = FirebaseDatabase.getInstance().getReference().child("Attendence");


        mDisplayDate = (TextView) findViewById(R.id.attendenceT1);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("Batch_Student").child(batchName);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            cnt = (int) dataSnapshot.getChildrenCount();
                            studentlist = new String[cnt];
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (int j = 1; j <= cnt; j++) {
                            studentlist[j - 1] = dataSnapshot.child(String.valueOf(j)).getValue().toString();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Attendence.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                final String date = day + "-" + month + "-" + year;
                cDate = date;
                mDisplayDate.setText(date);
                Toast.makeText(getApplicationContext(), String.valueOf(cnt4), Toast.LENGTH_SHORT).show();
                reference4.child(String.valueOf(cnt4)).setValue(date);

                for (int i = 1; i <= cnt; i++) {
                    reference3.child(courseId).child(batchName).child(date).child(studentlist[i - 1]).setValue("A");
                }
                reference5 = FirebaseDatabase.getInstance().getReference().child("Attendence_QrCode").child(courseId).child(batchName).child(cDate);
                reference5.setValue(qrCode);
            }
        };
    }

    String getDate() {
        return cDate;
    }
}
