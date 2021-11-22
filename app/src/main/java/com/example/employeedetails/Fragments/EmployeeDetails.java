package com.example.employeedetails.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.employeedetails.Activities.ChangePhotoActivity;
import com.example.employeedetails.R;

import java.io.File;


public class EmployeeDetails extends Fragment {
    TextView b;
    ImageView viewImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_employee_details, container, false);
        getActivity().setTitle("My Profile");
        b=v.findViewById(R.id.btnSelectPhoto);
        viewImage=v.findViewById(R.id.shapeableImageView);
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

}