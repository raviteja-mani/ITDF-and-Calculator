package com.example.employeedetails.Fragments.Calculator;

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
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

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

        CalculatorViewModel viewModel=new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        interest.setText(String.valueOf(otherincome.getInterestOnSavings()));
        otherSourses.setText(String.valueOf(otherincome.getOtherIncome()));


        FragmentManager manager=getActivity().getSupportFragmentManager();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.setOtherIncomeItem(new Otherimcome(Integer.parseInt(String.valueOf(interest.getText())),Integer.parseInt(String.valueOf(otherSourses.getText()))));
                manager.popBackStack();
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