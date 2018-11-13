package com.example.ianoderon.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    EditText eFname, eLname, ePoints;

    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);

        res = helper.selectRecords();

        eFname = findViewById(R.id.etFname);
        eLname = findViewById(R.id.etLname);
        ePoints = findViewById(R.id.etPoints);

    }

    public void moveFirst(View v) {
        res.moveToFirst();

        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);
    }

    public void movePrevious(View v){
        res.moveToPrevious();

        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);
    }

    public void moveNext(View v) {
        res.moveToNext();

        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);
    }

    public void moveLast(View v) {
        res.moveToLast();

        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);
    }


    public void deleteRecord() {
        String id = res.getString(0);
        Integer countRow = helper.delete(id);

        if(countRow == 1)
            Toast.makeText(this, "Deleted 1 record...", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Deleted unsuccessful...", Toast.LENGTH_LONG).show();

        res = helper.selectRecords();
    }

    public void editRecord(View v) {

        String firstName = eFname.getText().toString().trim();
        String lastName = eLname.getText().toString().trim();
        int points = Integer.parseInt(ePoints.getText().toString());

        String id = res.getString(0);
        boolean isUpdated = helper.update(id, firstName, lastName, points);

        if(isUpdated)
            Toast.makeText(this, "Record updated...", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Record error...", Toast.LENGTH_LONG).show();

        res = helper.selectRecords();
    }

    public void insertRecord(View v) {
        String firstName = eFname.getText().toString().trim();
        String lastName = eLname.getText().toString().trim();
        int points = Integer.parseInt(ePoints.getText().toString());

        boolean isInserted = helper.insert(firstName, lastName, points);

        if(isInserted)
            Toast.makeText(this, "Record saved...", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Record error...", Toast.LENGTH_LONG).show();

        res = helper.selectRecords();

    }


}
