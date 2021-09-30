package com.example.employeedetails;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class EmployeeDetails extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_employee_details, container, false);
        Button btn=(Button) v.findViewById(R.id.nextTo);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().
                        addToBackStack("Good").replace(R.id.frameLayoutContainer,new GuideLinesFragment()).commit();
            }
        });
        return v;
    }
}