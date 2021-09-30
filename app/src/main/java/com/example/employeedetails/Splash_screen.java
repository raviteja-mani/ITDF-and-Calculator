package com.example.employeedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Splash_screen extends AppCompatActivity {
    ImageView image;
//    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=findViewById(R.id.imageView);
//        progress=findViewById(R.id.progressBar);
        new Handler().postDelayed((Runnable) () -> {
        Intent i = new Intent(this,Login_page.class);
        startActivity(i);
        finish();
        },5000);
    }
}