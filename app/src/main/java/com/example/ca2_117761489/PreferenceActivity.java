package com.example.ca2_117761489;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PreferenceActivity extends AppCompatActivity {

    //Code adapted from https://www.youtube.com/watch?v=fJEFZ6EOM9o

    SwitchCompat swPref;
    Button btnSavePref;
    Button btnBack;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        swPref = findViewById(R.id.swPref);
        btnSavePref = findViewById(R.id.btnSavePref);

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
                        startActivity(new Intent(PreferenceActivity.this, MainActivity.class));
                        break;
                    case R.id.action_settings:
                        startActivity(new Intent(PreferenceActivity.this, PreferenceActivity.class));
                        break;
                }
                return true;
            }
        });

        btnSavePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(com.example.ca2_117761489.PreferenceActivity.this, "Preference Saved", Toast.LENGTH_SHORT).show();
            }
        });

        loadData();
        updateViews();
    }

    //Saving preference
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SWITCH1, swPref.isChecked());
        editor.apply();
    }

    //Check current preference
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,true);

    }
    //Set the switch on/off
    public void updateViews() {
        swPref.setChecked(switchOnOff);
    }
}