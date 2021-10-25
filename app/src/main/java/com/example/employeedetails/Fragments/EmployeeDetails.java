package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.Fragments.GuideLinesFragment;
import com.example.employeedetails.R;


public class EmployeeDetails extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      getActivity().setTitle("My Profile");
        View v= inflater.inflate(R.layout.fragment_employee_details, container, false);
        return v;
    }
}