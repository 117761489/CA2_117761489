package com.example.ca2_117761489;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    EditText editName;
    EditText editAge;
    TextView tvStaffID;
    SwitchCompat swEditActive;
    DatabaseHelper databaseHelper;
    StaffModel staff;
    List<StaffModel> staffList = new ArrayList<StaffModel>();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        editName = findViewById(R.id.etEditName);
        editAge = findViewById(R.id.etAge);
        tvStaffID = findViewById(R.id.tvstaffID);
        swEditActive = findViewById(R.id.swEditActive);

        //Connect to database, populate list
        databaseHelper = new DatabaseHelper(EditActivity.this);
        staffList = databaseHelper.getEveryone();

        //Getting the ID that the main activity passed
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        //For each loop to find the correct student based on ID
        if (id >= 0) {
            for (StaffModel s : staffList
            ) {
                if (s.getId() == id) {
                    staff = s;
                }
            }
        }
        //Setting the values
        editName.setText(staff.getName());
        editAge.setText(String.valueOf(staff.getAge()));
        swEditActive.setChecked(staff.isCurrent());
        tvStaffID.setText(String.valueOf(staff.getId()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editName.getText().toString().matches("") ||
                        (editAge.getText().toString().matches("")))  {
                    Toast.makeText(EditActivity.this, "Please ensure all fields are filled",
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    //Update the record based on User Inputs
                    staff.setName(editName.getText().toString());
                    staff.setAge(Integer.parseInt(editAge.getText().toString()));
                    staff.setCurrent(swEditActive.isChecked());
                    databaseHelper.updateOne(staff);
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                } }});

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete the record
                databaseHelper.deleteOne(staff);
                Toast.makeText(EditActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Return to main activity
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}