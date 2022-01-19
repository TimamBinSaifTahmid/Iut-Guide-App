package com.example.iutguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Events extends AppCompatActivity {
    Intent intent= getIntent();
    TextView event1;
    TextView event2;
    TextView event3;
    TextView time1;
    TextView time2;
    TextView time3;
    TextView venue1;
    TextView venue2;
    TextView venue3;
    TextView description1;
    TextView description2;
    TextView description3;

    DatabaseReference eventData1 ;
    DatabaseReference eventData2 ;
    DatabaseReference eventData3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
       event1=(TextView)findViewById(R.id.event1);
       event2=(TextView)findViewById(R.id.event2);
       event3=(TextView)findViewById(R.id.event3);
       venue1=(TextView)findViewById(R.id.venue1);
       venue2=(TextView)findViewById(R.id.venue2);
       venue3=(TextView)findViewById(R.id.venue3);
       time1=(TextView)findViewById(R.id.time1);
       time2=(TextView)findViewById(R.id.time2);
       time3=(TextView)findViewById(R.id.time3);
       description1=(TextView)findViewById(R.id.description1);
       description2=(TextView)findViewById(R.id.description2);
       description3=(TextView)findViewById(R.id.description3);
       eventData1 = FirebaseDatabase.getInstance().getReference("Event/event1");
       //eventData.setValue("hello world");
       eventData1.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               //for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                  // EventDatabase eventDatabase = dataSnapshot.getValue(EventDatabase.class);
              // }
               String value = dataSnapshot.getValue(String.class);

               event1.setText(value);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/vanue1");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                venue1.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/time1");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                time1.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/description1");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                description1.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/event2");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                event2.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/vanue2");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                venue2.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/time2");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                time2.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/description2");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                description2.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData3 = FirebaseDatabase.getInstance().getReference("Event/event3");

        eventData3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                event3.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/vanue3");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                venue3.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/time3");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                time3.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventData2 = FirebaseDatabase.getInstance().getReference("Event/description3");

        eventData2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                description3.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
