package com.example.employeedetails.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_page extends AppCompatActivity {

    LinearLayout loginpage;
    Button loginBtn;
    Button signupbutton;
    EditText username;
    EditText password;
    TextView error;
    AppSession session;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginpage=findViewById(R.id.loginpage);
        progressBar=findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        if (!isNetworkAvailable(this)) {
            showSnackbar(getResources().getString(R.string.noInternet),loginpage);
        }
        signupbutton=findViewById(R.id.signupbutton);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login_page.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        loginpage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                int action = event.getActionMasked();
                return true;
            }
        });
        username=findViewById(R.id.username);
        password=findViewById(R.id.password_edit_text);
        error=findViewById(R.id.loginerror);
        session=new AppSession(this.getApplicationContext());
        loginBtn=findViewById(R.id.Loginbutton);
        getSupportActionBar().setTitle("Please Login");

        loginBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            hideKeyboard(view);
            if(authenticate(String.valueOf(username.getText()),String.valueOf(password.getText())))
            signin(String.valueOf(username.getText()),String.valueOf(password.getText()));

        });
    }
    public boolean authenticate(String email,String pass){
        if(email.equals("")||pass.equals("")) return false;
        return true;
    }
    public void signin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Intent i = new Intent(Login_page.this, MainActivity.class);
                            session.setUserName(email);
                            session.setUserPassword(password);
                            startActivity(i);

                            error.setVisibility(View.INVISIBLE);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            error.setVisibility(View.VISIBLE);
                        }
                    }
                });

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
    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {

        }
    }
}