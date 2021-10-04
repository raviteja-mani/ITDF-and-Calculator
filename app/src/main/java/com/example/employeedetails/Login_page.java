package com.example.employeedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class Login_page extends AppCompatActivity {
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginBtn=findViewById(R.id.Loginbutton);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.stohrm_logo);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}