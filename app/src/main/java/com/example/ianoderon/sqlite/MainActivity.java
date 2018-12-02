package com.example.ianoderon.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference points;

    EditText eFname, eLname, ePoints;

    ArrayList<String> keyList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = FirebaseDatabase.getInstance("https://sqlite-caf54.firebaseio.com/");
        points = db.getReference("points");

        eFname = findViewById(R.id.etFname);
        eLname = findViewById(R.id.etLname);
        ePoints = findViewById(R.id.etPoints);

        keyList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss : dataSnapshot.getChildren())
                    keyList.add(ss.getKey());
//                Toast.makeText(this, )
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void moveFirst(View v) {
        index = 0;
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                ePoints.setText(stud.getPoints().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    protected void Toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void movePrevious(View v) {
        index--;
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                ePoints.setText(stud.getPoints().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void moveNext(View v) {
        index++;
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                ePoints.setText(stud.getPoints().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void moveLast(View v) {
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                index = (int) dataSnapshot.getChildrenCount() - 1;

                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                ePoints.setText(stud.getPoints().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void deleteRecord() {
//        points.child(keyList.get(index - 1)).removeValue();

//        eFname.setText("");
//        eLname.setText("");
//        ePoints.setText("");

        Toast("" + index);

    }

    public void editRecord(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long score = Long.parseLong(ePoints.getText().toString().trim());

        Student stud = new Student(fname, lname, score);
        points.child(keyList.get(index)).setValue(stud);

        Toast("record updated");
    }

    public void insertRecord(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long score = Long.parseLong(ePoints.getText().toString().trim());

        String key = points.push().getKey();

        Student stud = new Student(fname, lname, score);

        points.child(key).setValue(stud);

        Toast("record inserted");
    }


}
