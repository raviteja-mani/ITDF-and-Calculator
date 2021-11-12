package com.example.employeedetails.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;

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
            AppSession session=new AppSession(this);
            if(session.getUserName()!=null)
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
            else {
                Intent i = new Intent(this, Login_page.class);
                startActivity(i);
                finish();
            }
        },5000);    
    }


}