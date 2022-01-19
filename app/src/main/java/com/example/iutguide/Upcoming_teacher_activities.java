package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Upcoming_teacher_activities extends AppCompatActivity {
    Intent intent= getIntent();
     int cnt,cnt2,cnt3,cnt4,temp=0,temp2=0,temp3=0,temp4=0;
    String course_name_array[];
    String title[]=new String[100];
    String time[]= new String[100];
    String date[]=new String[100];
    String generaliz_string[]=new String[100];
    ListView teacher_upcoming_activity;
    private SimpleDateFormat dateFormat;
    private String datee;
    private String currentMonth;
    private String currentDatte;
    private String currentDay;
    private String dateCheck;
    private String dateCheckFinal;

    DatabaseReference course_name;
    DatabaseReference quiz_ref;
    DatabaseReference assignment_ref;
    DatabaseReference presentation_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_teacher_activities);

        Calendar calendar = Calendar.getInstance();
        final String currentDate= DateFormat.getDateInstance().format(calendar.getTime());

        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        datee = dateFormat.format(calendar.getTime());


        final char month[] = new char[2];
        datee.getChars(0, 2, month, 0);
        currentMonth = String.valueOf(month);

        final char datte[] = new char[2];
        datee.getChars(3, 5, datte, 0);
        currentDatte = String.valueOf(datte);

        currentDay = currentMonth+currentDatte;

       // final ArrayAdapter<String> myArrayAdepter=new ArrayAdapter<String>(Upcoming_teacher_activities.this,android.R.layout.simple_list_item_1,generaliz_string);
        teacher_upcoming_activity=(ListView)findViewById(R.id.teacher_upcoming_activity);
        final CustomAdapter adapter= new CustomAdapter(this,generaliz_string);
        teacher_upcoming_activity.setAdapter(adapter);
        LogIn logIn=new LogIn();
        String str;
        str=logIn.StudentId();
        course_name= FirebaseDatabase.getInstance().getReference().child("Teacher_Course").child(str);
        quiz_ref= FirebaseDatabase.getInstance().getReference().child("Quiz");
        assignment_ref= FirebaseDatabase.getInstance().getReference().child("Assignment");
        presentation_ref= FirebaseDatabase.getInstance().getReference().child("Presentation");

        course_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    cnt=(int)dataSnapshot.getChildrenCount();

                    course_name_array= new String[cnt];
                    for(int i=1;i<=cnt;i++){
                        course_name_array[i-1]=dataSnapshot.child(String.valueOf(i)).child("courseId").getValue().toString();
                       // Toast.makeText(getApplicationContext(),String.valueOf(course_name_array[i-1]),Toast.LENGTH_SHORT).show();
                        generaliz_string[temp4++]="Course : "+course_name_array[i-1];
                        final String courseID=course_name_array[i-1];
                       // Toast.makeText(getApplicationContext(),String.valueOf(courseName),Toast.LENGTH_SHORT).show();
                        final int finalI = i;
                        final int finalI1 = i;
                        final int finalI2 = i;
                        final int finalI3 = i;

                        quiz_ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                if(dataSnapshot1.child(courseID).exists()) {
                                    cnt2 = (int) dataSnapshot1.child(courseID).getChildrenCount();
                                   // Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                                    for(int j=1;j<=cnt2;j++){
                                        dateCheck = dataSnapshot1.child(courseID).child(String.valueOf(j)).child("task_date").getValue().toString();
                                        dateCheckFinal = dateCheck.substring(0,2)+dateCheck.substring(3,5);
                                        if(currentDay.compareTo(dateCheckFinal)<0) {
                                            title[temp++] = dataSnapshot1.child(course_name_array[finalI1 - 1]).child(String.valueOf(j)).child("task_title").getValue().toString();
                                            generaliz_string[temp4++] = " Quiz Title : " + title[temp - 1];
                                            time[temp2++] = dataSnapshot1.child(course_name_array[finalI1 - 1]).child(String.valueOf(j)).child("task_time").getValue().toString();
                                            generaliz_string[temp4++] = " Time : " + time[temp2 - 1];
                                            date[temp3++] = dataSnapshot1.child(course_name_array[finalI1 - 1]).child(String.valueOf(j)).child("task_date").getValue().toString();
                                            generaliz_string[temp4++] = " date : " + date[temp3 - 1];
                                            //  Toast.makeText(getApplicationContext(),String.valueOf(generaliz_string[temp4-1]),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText(getApplicationContext(),String.valueOf(title[temp-1]),Toast.LENGTH_SHORT).show();
                                            //  Toast.makeText(getApplicationContext(),String.valueOf(time[temp2-1]),Toast.LENGTH_SHORT).show();
                                            //  Toast.makeText(getApplicationContext(),String.valueOf(date[temp3-1]),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                      //  Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                        assignment_ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                if(dataSnapshot2.child(courseID).exists()) {
                                    cnt3 = (int) dataSnapshot2.child(courseID).getChildrenCount();
                                    // Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                                    for(int j=1;j<=cnt3;j++){
                                        dateCheck = dataSnapshot2.child(courseID).child(String.valueOf(j)).child("task_date").getValue().toString();
                                        dateCheckFinal = dateCheck.substring(0,2)+dateCheck.substring(3,5);
                                        if(currentDay.compareTo(dateCheckFinal)<0) {
                                            title[temp++] = dataSnapshot2.child(course_name_array[finalI2 - 1]).child(String.valueOf(j)).child("task_title").getValue().toString();
                                            generaliz_string[temp4++] = " Assignment Title : " + title[temp - 1];
                                            time[temp2++] = dataSnapshot2.child(course_name_array[finalI2 - 1]).child(String.valueOf(j)).child("task_time").getValue().toString();
                                            generaliz_string[temp4++] = " Time : " + time[temp2 - 1];
                                            date[temp3++] = dataSnapshot2.child(course_name_array[finalI2 - 1]).child(String.valueOf(j)).child("task_date").getValue().toString();
                                            generaliz_string[temp4++] = " Date : " + date[temp3 - 1];
                                            // Toast.makeText(getApplicationContext(),String.valueOf(generaliz_string[temp4-1]),Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),String.valueOf(title[temp-1]),Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),String.valueOf(time[temp2-1]),Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),String.valueOf(date[temp3-1]),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                       // Toast.makeText(getApplicationContext(),String.valueOf(cnt3),Toast.LENGTH_SHORT).show();
                        presentation_ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
                                if(dataSnapshot3.child(courseID).exists()) {
                                    cnt4 = (int) dataSnapshot3.child(courseID).getChildrenCount();
                                    // Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                                    for(int j=1;j<=cnt4;j++){
                                        dateCheck = dataSnapshot3.child(courseID).child(String.valueOf(j)).child("task_date").getValue().toString();
                                        dateCheckFinal = dateCheck.substring(0,2)+dateCheck.substring(3,5);
                                        if(currentDay.compareTo(dateCheckFinal)<0) {
                                            title[temp++] = dataSnapshot3.child(course_name_array[finalI3 - 1]).child(String.valueOf(j)).child("task_title").getValue().toString();
                                            generaliz_string[temp4++] = "Presentation Title : " + title[temp - 1];
                                            time[temp2++] = dataSnapshot3.child(course_name_array[finalI3 - 1]).child(String.valueOf(j)).child("task_time").getValue().toString();
                                            generaliz_string[temp4++] = " time : " + time[temp2 - 1];
                                            date[temp3++] = dataSnapshot3.child(course_name_array[finalI3 - 1]).child(String.valueOf(j)).child("task_date").getValue().toString();
                                            generaliz_string[temp4++] = " Date : " + date[temp3 - 1];
                                            // Toast.makeText(getApplicationContext(),String.valueOf(generaliz_string[temp4-1]),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText(getApplicationContext(),String.valueOf(title[temp-1]),Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),String.valueOf(time[temp2-1]),Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),String.valueOf(date[temp3-1]),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        //Toast.makeText(getApplicationContext(),String.valueOf(cnt4),Toast.LENGTH_SHORT).show();
                    }
            }
                Toast.makeText(getApplicationContext(),String.valueOf(cnt2),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),String.valueOf(cnt3),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),String.valueOf(cnt4),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
