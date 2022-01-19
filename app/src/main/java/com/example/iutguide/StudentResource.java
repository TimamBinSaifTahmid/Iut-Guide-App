package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentResource extends AppCompatActivity {
    Intent intent = getIntent();
    ListView resourceList;
    Button reload;
    private DatabaseReference studentBatch;
    private DatabaseReference courses;
    private DatabaseReference resourcesList;
    private DatabaseReference selectedCourse;
    String batchName;
    String courseList[];
    String finalCourse;
    String[] resourceTitles;
    String[] resourceLinks;
    int cnt=0, listCount=0, temp1=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resource);

        LogIn logIn=new LogIn();
        String SID= logIn.StudentId();
        Student_Course courseName = new Student_Course();
        final int coursePosition = courseName.position();

        studentBatch= FirebaseDatabase.getInstance().getReference().child("Batch_Selected").child(SID);
        studentBatch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                batchName = dataSnapshot.getValue().toString();

                selectedCourse = FirebaseDatabase.getInstance().getReference().child("Batch_Course").child(batchName).child(String.valueOf(coursePosition));
                selectedCourse.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        finalCourse = dataSnapshot.getValue().toString();

                        resourcesList = FirebaseDatabase.getInstance().getReference().child("Resource").child(finalCourse);
                        resourcesList.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    listCount = (int) dataSnapshot.getChildrenCount();
                                    resourceLinks = new String[listCount];
                                    resourceTitles = new String[listCount];

                                    for (int j = 1;j<=listCount;j++){

                                        resourceTitles[temp1] =dataSnapshot.child(String.valueOf(j)).child("resourceTitle").getValue().toString();
                                        resourceLinks[temp1] = dataSnapshot.child(String.valueOf(j)).child("resourceLink").getValue().toString();

                                        temp1++;
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

        reload = findViewById(R.id.reloadResourceButton);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayAdapter<String> myArrayAdepter=new ArrayAdapter<String>(StudentResource.this,android.R.layout.simple_list_item_1,resourceTitles);
                resourceList=(ListView) findViewById(R.id.resourceListview);

                resourceList.setAdapter(myArrayAdepter);

                resourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        for (int i = 0; i < temp1; i++) {
                            if (position == i) {
                                ActivateLink(resourceLinks[i]);
                            }
                        }
                    }
                });

            }
        });
    }

   public void ActivateLink(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
}