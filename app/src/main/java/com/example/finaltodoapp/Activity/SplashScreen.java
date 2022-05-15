package com.example.finaltodoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.finaltodoapp.MainActivity;
import com.example.finaltodoapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
//        will hide the tool bar for only this
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                after creating intent with 2s this will take it to MainActivity
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        },2000);
    }
}