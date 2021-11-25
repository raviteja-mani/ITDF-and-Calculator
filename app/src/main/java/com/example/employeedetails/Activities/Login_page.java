package com.example.employeedetails.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.employeedetails.Activities.MainActivity;
import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;
import com.google.android.material.textfield.TextInputEditText;

public class Login_page extends AppCompatActivity {
    Button loginBtn;
    EditText username;
    EditText password;
    TextView error;
    AppSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password_edit_text);
        error=findViewById(R.id.loginerror);
        session=new AppSession(this.getApplicationContext());
//        getSupportActionBar().setTitle("Login Please");
        loginBtn=findViewById(R.id.Loginbutton);
        getSupportActionBar().setTitle("Please Login");
//        getSupportActionBar().setLogo(R.drawable.stohrm_logo);
        loginBtn.setOnClickListener(view -> {
            if(String.valueOf(username.getText()).equals("raviteja")&&String.valueOf(password.getText()).equals("raviteja"))
            {
                Intent i = new Intent(this, MainActivity.class);
                session.setUserName(String.valueOf(username.getText()));
                session.setUserPassword(String.valueOf(password.getText()));
                startActivity(i);
                error.setVisibility(View.INVISIBLE);
                finish();
            }
            else{
                error.setVisibility(View.VISIBLE);
            }
        });
    }
}