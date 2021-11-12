package com.example.employeedetails.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeedetails.ModalClasses.Otherimcome;
import com.example.employeedetails.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class IncomeFromOSFragment extends Fragment {
    EditText interest;
    EditText otherSourses;
    Button submit;
    Button cancel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_income_from_os, container, false);
        Bundle b=getArguments();
        Otherimcome otherincome=(Otherimcome) b.getSerializable("other income");
        interest=v.findViewById(R.id.OSrow1);
        otherSourses=v.findViewById(R.id.OSrow2);
        submit=v.findViewById(R.id.btnsubmitPassword);
        cancel=v.findViewById(R.id.btnCancel);
        SharedPreferences shr=getActivity().getSharedPreferences("employeeDetails",Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor=shr.edit();
        interest.setText(String.valueOf(otherincome.getInterestOnSavings()));
        otherSourses.setText(String.valueOf(otherincome.getOtherIncome()));


        FragmentManager manager=getActivity().getSupportFragmentManager();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefsEditor.putInt("InterestOnSavings",Integer.parseInt(String.valueOf(interest.getText()))).commit();
                prefsEditor.putInt("otherIncome",Integer.parseInt(String.valueOf(otherSourses.getText()))).commit();
                Fragment fragment=new CalculatorFragment();
                manager.beginTransaction().replace(R.id.frameLayoutContainer,fragment).commit();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.popBackStack();
            }
        });
        return v;
    }
}