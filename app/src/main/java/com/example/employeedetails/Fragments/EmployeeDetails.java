package com.example.employeedetails.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.employeedetails.Activities.ChangePhotoActivity;
import com.example.employeedetails.AppSession;
import com.example.employeedetails.ModalClasses.User;
import com.example.employeedetails.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.File;


public class EmployeeDetails extends Fragment {
    TextView b;
    ImageView viewImage;
    AppSession session;
    TextView empcode;
    TextView designation;
    TextView username;
    TextView email;
    TextView company;
    private FirebaseAuth mAuth;

    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_employee_details, container, false);
        getActivity().setTitle("My Profile");

        empcode=v.findViewById(R.id.empid);
        designation=v.findViewById(R.id.designationED);
        username=v.findViewById(R.id.empname);
        email=v.findViewById(R.id.email);
        company=v.findViewById(R.id.company);

        mAuth=FirebaseAuth.getInstance();
        b=v.findViewById(R.id.btnSelectPhoto);
        viewImage=v.findViewById(R.id.shapeableImageView);
        session=new AppSession(getActivity().getApplicationContext());
        user=session.getUser();
        empcode.setText(String.valueOf(user.getUserId()));
        designation.setText(user.getDesignation());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        company.setText(user.getCompanyName());
//        session.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.profile));
        viewImage.setImageBitmap(session.getProfileBitmap());
//        }
//        catch(Exception e){
//
//        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectImage();
                Intent i=new Intent(getActivity(), ChangePhotoActivity.class);
                startActivity(i);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                MyProfileChangePhoto newFragment = new MyProfileChangePhoto();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewImage.setImageBitmap(session.getProfileBitmap());
    }
//    public void getUserData(){
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        User user1=new User();
//        DatabaseReference reference=database.getReference("Users").child(user.getUid());
//        System.out.println(user.getUid());
////System.out.println(reference.child("companyname").getValue().toString());
//        reference.child("companyname").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user1.setCompanyName(snapshot.getValue().toString());
//                company.setText(user1.getCompanyName());
////                error.setVisibility(View.VISIBLE);
////                error.setText(snapshot.getValue().toString());
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
//                username.setText(user1.getUsername());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        session.setUser(user1);
////        reference.child("password").setValue(password);
//    }
}