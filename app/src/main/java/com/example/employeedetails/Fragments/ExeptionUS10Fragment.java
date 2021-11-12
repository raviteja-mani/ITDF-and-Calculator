package com.example.employeedetails.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.R;

public class ExeptionUS10Fragment extends Fragment {
    Spinner spinner;
    EditText exemptionBaseSalary;
    EditText exemptionDA;
    EditText exemptionHRARecieved;
    EditText exemptionTotalRentPaid;
    Button submitbtn;
    Button cancelbtn;
    Generalfunctions funcs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_exeption_us10, container, false);
        getActivity().setTitle("Exception10");
        funcs=new Generalfunctions(getContext());
        exemptionBaseSalary=v.findViewById(R.id.exemptionBaseSalary);
        exemptionDA=v.findViewById(R.id.exemptionDA);
        exemptionHRARecieved=v.findViewById(R.id.exemptionHRARecieved);
        exemptionTotalRentPaid=v.findViewById(R.id.exemptionTotalRentPaid);
        submitbtn=v.findViewById(R.id.exemptionSubmit);
        cancelbtn=v.findViewById(R.id.exemptioncancel);
        spinner=v.findViewById(R.id.metrospinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.programming_languages,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val;

                if(Integer.parseInt(String.valueOf(spinner.getSelectedItemPosition()))==1)
                val=("yes");
                else val=("No");
//                e.setBaseSalary();
//                e.setDearnessAllowance();
//                e.setHRARecieved();
//                e.setTotalRentPaid();
                Exemptions e=new Exemptions(Integer.parseInt(String.valueOf(exemptionBaseSalary)),Integer.parseInt(String.valueOf(exemptionDA)),Integer.parseInt(String.valueOf(exemptionHRARecieved)),Integer.parseInt(String.valueOf(exemptionTotalRentPaid)),val);
                funcs.setExemptions(e);
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f=new CalculatorFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
                manager.beginTransaction().addToBackStack("calculator").replace(R.id.frameLayoutContainer,f).commit();
            }
        });
        return v;
    }

}