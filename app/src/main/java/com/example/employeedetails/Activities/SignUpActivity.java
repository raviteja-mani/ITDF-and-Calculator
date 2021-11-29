package com.example.employeedetails.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
EditText companyname;
EditText username;
EditText emailId;
EditText password_edit_text;
Button signUp;
TextView error;
private FirebaseAuth auth;
AppSession session;
FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference= database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// ...
// Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);
        error=findViewById(R.id.signuperror);
        session=new AppSession(getApplicationContext());
        companyname=findViewById(R.id.companyname);
        username=findViewById(R.id.username);
        emailId=findViewById(R.id.emailId);
        password_edit_text=findViewById(R.id.password_edit_text);
        signUp=findViewById(R.id.SignUpbutton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(String.valueOf(emailId.getText()),String.valueOf(password_edit_text.getText()));

            }
        });
    }
    public void signup(String email,String passward){

        auth.createUserWithEmailAndPassword(email,passward)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveTheData(String.valueOf(username.getText()),String.valueOf(companyname.getText()),String.valueOf(emailId.getText()),String.valueOf(password_edit_text.getText()));
                            // Sign in success, update UI with the signed-in user's information
                            Intent i =new Intent(SignUpActivity.this,Login_page.class);
                            startActivity(i);
                            finish();
                        } else {
                            error.setVisibility(View.VISIBLE);
                            // If sign in fails, display a message to the user.
                        }
                    }
                });
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }
    public void saveTheData(String username,String companyname,String email,String password){
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference().child("Users").child(user.getUid());
        reference.child("companyname").setValue(companyname);
        reference.child("emailId").setValue(email);
        reference.child("password").setValue(password);
        reference.child("username").setValue(username);
    }
}