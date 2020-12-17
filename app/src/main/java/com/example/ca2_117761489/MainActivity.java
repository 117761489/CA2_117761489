package com.example.ca2_117761489;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //References to controls
    Button btnAdd;
    EditText etStaffNames, etAge;
    Switch swCurrentStaff;
    RecyclerView rvStaff;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<StaffModel> staffList = new ArrayList<StaffModel>();


    ArrayAdapter<StaffModel> staffArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etStaffNames = findViewById(R.id.etStaffName);
        etAge = findViewById(R.id.etAge);
        swCurrentStaff = findViewById(R.id.swCurrentStaff);
        rvStaff = findViewById(R.id.rvStaff);

        //Connecting to database, populate list
        databaseHelper = new DatabaseHelper(MainActivity.this);
        staffList = databaseHelper.getEveryone();

        //Displaying the recycler
        rvStaff.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvStaff.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(staffList,this);
        rvStaff.setAdapter(mAdapter);

        //https://suragch.medium.com/how-to-add-a-bottom-navigation-bar-in-android-958ed728ef6c
        //Article sited above used as a source for bottom navigation code
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    //Each case runs depending on which option is pressed on the nav bar
                    case R.id.action_home:
                        //Intents switch to the next activity, this one will just reload the Main Activity
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.action_settings:
                        startActivity(new Intent(MainActivity.this, PreferenceActivity.class));
                        break;
                }
                return true;
            }
            });

        //Button Listeners
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                StaffModel staffModel;
                //Checking fields are not empty
                if ((etStaffNames.getText().toString().matches("")) ||
                        (etAge.getText().toString().matches(""))) {
                    Toast.makeText(MainActivity.this, "Please ensure Name & Age are not empty",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        staffModel = new StaffModel(-1,etStaffNames.getText().toString(),
                                Integer.parseInt(etAge.getText().toString()), swCurrentStaff.isChecked() );
                    }
                    catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        staffModel = new StaffModel(-1,"error",0,false );
                    }
                    boolean success = databaseHelper.addOne(staffModel);
                    Toast.makeText(MainActivity.this, "Record Added", Toast.LENGTH_SHORT).show();

                    //Displaying the new list
                    staffList = databaseHelper.getEveryone();
                    rvStaff.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    rvStaff.setLayoutManager(layoutManager);
                    mAdapter = new RecycleViewAdapter(staffList,MainActivity.this);
                    rvStaff.setAdapter(mAdapter);

            }
        }
        });

}
}