package com.example.employeedetails.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    LinearLayout signup;
EditText companyname;
EditText username;
EditText emailId;
EditText password_edit_text;
EditText designation;
EditText empno;
Button signUp;
TextView error;
private FirebaseAuth auth;
AppSession session;
ProgressBar signupprogressBar;
FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference= database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);
        signupprogressBar=findViewById(R.id.signupprogressBar);
        setTitle("Please SignUp");
        error=findViewById(R.id.signuperror);
        session=new AppSession(getApplicationContext());
        companyname=findViewById(R.id.companyname);
        username=findViewById(R.id.username);
        emailId=findViewById(R.id.emailId);
        password_edit_text=findViewById(R.id.password_edit_text);
        designation=findViewById(R.id.designation);
        empno=findViewById(R.id.employeecode);
        signUp=findViewById(R.id.SignUpbutton);
        signup=findViewById(R.id.signup);
        signup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                int action = event.getActionMasked();
                return true;
            }
        });
        if (!isNetworkAvailable(this)) {
            showSnackbar(getResources().getString(R.string.noInternet),signup);
        }
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupprogressBar.setVisibility(View.VISIBLE);
                hideKeyboard(view);
                if(!authenticateValue())
                signup(String.valueOf(emailId.getText()),String.valueOf(password_edit_text.getText()));
                else{
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please enter the valid values!");
                    signupprogressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    public boolean authenticateValue(){
        if(!String.valueOf(emailId.getText()).equals("")||!String.valueOf(password_edit_text.getText()).equals("")||!String.valueOf(companyname.getText()).equals("")||!String.valueOf(username.getText()).equals("")||!String.valueOf(empno.getText()).equals("")||!String.valueOf(designation.getText()).equals("")) return false;
        return true;
    }
    public void signup(String email,String passward){

        auth.createUserWithEmailAndPassword(email,passward)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveTheData(String.valueOf(username.getText()),String.valueOf(companyname.getText()),String.valueOf(emailId.getText()),String.valueOf(password_edit_text.getText()),String.valueOf(empno.getText()),String.valueOf(designation.getText()));
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
//        userFirebaseUser user = auth.getCurrentUser();
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName("Jane Q. Us

    }
    public void onStart() {
        super.onStart();
    }
    public void saveTheData(String username,String companyname,String email,String password,String empcode,String designation){
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference().child("Users").child(user.getUid());
        reference.child("companyname").setValue(companyname);
        reference.child("emailId").setValue(email);
        reference.child("password").setValue(password);
        reference.child("username").setValue(username);
        reference.child("employeecode").setValue(empcode);
        reference.child("designation").setValue(designation);
    }
    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {

        }
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        return isConnected;
    }
    public static void showSnackbar(String message, View view) {
        try {
            //view = (CoordinatorLayout) view.findViewById(R.id.snackbarlocation);
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#D07A23"));
            TextView textView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } catch (Exception ex) {
            // Catch exception here
        }
    }
}