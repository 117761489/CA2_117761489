package com.example.ca2_117761489;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    //Code adapted from https://www.youtube.com/watch?v=jXtof6OUtcE

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";
    private boolean switchOnOff;

    //Timer to determine how long the splash screen is shown for
    private static int Splash_Timer = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Check preferences to see if they have splash screen switched on/off
        loadData();

        if(switchOnOff == true) {
        new Handler().postDelayed(new Runnable () {
            @Override
            public void run() {
                Intent homeintent = new Intent(com.example.ca2_117761489.SplashActivity.this,MainActivity.class);
                startActivity(homeintent);
                finish();
            }
        }, Splash_Timer);}
        else {
            //Skipping splash and going straight to main activity (kind of works, Spash Screen does not stay as long)
            //Best i could get it to work
            Intent homeintent = new Intent(com.example.ca2_117761489.SplashActivity.this,MainActivity.class);
            startActivity(homeintent);
        }
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,true);

    }
}