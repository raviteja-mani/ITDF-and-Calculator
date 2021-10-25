package com.example.employeedetails.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.employeedetails.Activities.MainActivity;
import com.example.employeedetails.R;

public class Login_page extends AppCompatActivity {
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().setTitle("Login Please");
        loginBtn=findViewById(R.id.Loginbutton);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.stohrm_logo);
        loginBtn.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}