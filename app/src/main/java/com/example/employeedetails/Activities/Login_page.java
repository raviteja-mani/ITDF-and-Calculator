package com.example.employeedetails.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.employeedetails.Activities.MainActivity;
import com.example.employeedetails.AppSession;
import com.example.employeedetails.ModalClasses.User;
import com.example.employeedetails.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import okhttp3.internal.cache.DiskLruCache;

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
        username=findViewById(R.id.username);
        password=findViewById(R.id.password_edit_text);
        error=findViewById(R.id.loginerror);
        session=new AppSession(this.getApplicationContext());
        loginBtn=findViewById(R.id.Loginbutton);
        getSupportActionBar().setTitle("Please Login");

        loginBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
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
//    public void getUserData(){
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        User user1=session.getUser();
//        DatabaseReference reference=database.getReference("Users").child(user.getUid());
//System.out.println(user.getUid());
////System.out.println(reference.child("companyname").getValue().toString());
//        reference.child("companyname").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user1.setCompanyName(snapshot.getValue().toString());
//                error.setVisibility(View.VISIBLE);
//                error.setText(snapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
////        bmHgieKAiIOUhpAJaB88mXVup302
//        reference.child("emailId").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user1.setEmail(snapshot.getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        reference.child("username").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user1.setUsername(snapshot.getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
////        System.out.println(user1.getCompanyName());
////Log.d("tag1",user1.getUsername());
////Log.d("tag2",user1.getCompanyName());
//        session.setUser(user1);
////        reference.child("password").setValue(password);
//    }
}