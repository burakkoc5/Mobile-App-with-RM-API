package com.burakkoc.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.burakkoc.myapplication.view.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "firstTime";
    private static final String PREF_FIRST_TIME = "isFirstTime";
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        welcomeText = findViewById(R.id.welcome_text);

        // Check if the app is running for the first time
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstTime = prefs.getBoolean(PREF_FIRST_TIME, true);

        if (isFirstTime) {
            welcomeText.setText("Welcome!");
            prefs.edit().putBoolean(PREF_FIRST_TIME, false).apply();
        } else {
            welcomeText.setText("Hello!");
        }

        // Wait for a few seconds and start the main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500); // Wait for 2 seconds before starting the main activity
    }
}
