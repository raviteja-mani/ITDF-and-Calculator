package com.example.employeedetails.Fragments.Calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.employeedetails.Fragments.Calculator.CalculatorFragment;
import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

public class ExeptionUS10Fragment extends Fragment {
    Spinner spinner;
    EditText exemptionBaseSalary;
    EditText exemptionDA;
    EditText exemptionHRARecieved;
    EditText exemptionTotalRentPaid;
    Button submitbtn;
    Button cancelbtn;
    Generalfunctions funcs;
    CalculatorViewModel viewmodel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_exeption_us10, container, false);
        getActivity().setTitle("Exception10");
        Exemptions e=(Exemptions) (getArguments().getSerializable("exeption10"));
        viewmodel= new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
//        funcs=new Generalfunctions(getContext());
        exemptionBaseSalary=v.findViewById(R.id.exemptionBaseSalary);
        exemptionDA=v.findViewById(R.id.exemptionDA);
        exemptionHRARecieved=v.findViewById(R.id.exemptionHRARecieved);
        exemptionTotalRentPaid=v.findViewById(R.id.exemptionTotalRentPaid);
        submitbtn=v.findViewById(R.id.exemptionSubmit);
        cancelbtn=v.findViewById(R.id.exemptioncancel);
        spinner=v.findViewById(R.id.metrospinner);
        exemptionBaseSalary.setText(String.valueOf(e.getBaseSalary()));
        exemptionDA.setText(String.valueOf(e.getDearnessAllowance()));
        exemptionHRARecieved.setText(String.valueOf(e.getHRARecieved()));
        exemptionTotalRentPaid.setText(String.valueOf(e.getTotalRentPaid()));
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.programming_languages,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val="";
                if(Integer.parseInt(String.valueOf(spinner.getSelectedItemPosition()))==1)
                val="yes";
                else val="No";
                Exemptions e=new Exemptions(Long.parseLong(String.valueOf(exemptionBaseSalary.getText())),Long.parseLong(String.valueOf(exemptionDA.getText())),Long.parseLong(String.valueOf(exemptionHRARecieved.getText())),Long.parseLong(String.valueOf(exemptionTotalRentPaid.getText())),val);
                viewmodel.setExemptions(e);
                Fragment f=new CalculatorFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f=new CalculatorFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}