package com.example.employeedetails.Fragments.ITDF;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeedetails.R;

public class IncomeFragment extends Fragment {
    EditText incomeHPLetOut;
    EditText incomeHPselfoccupied;
    EditText incomeHPotherincome;
    EditText incomePEsalary;
    EditText incomePErecovered;
    EditText incomePEprovidentfund;
    EditText incomePEtaxpaidoutside;
    Button internalform;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_income, container, false);
        incomeHPLetOut = v.findViewById(R.id.incomeHPLetOut);
        incomeHPselfoccupied = v.findViewById(R.id.incomeHPselfoccupied);
        incomeHPotherincome = v.findViewById(R.id.incomeHPotherincome);
        incomePEsalary = v.findViewById(R.id.incomePEsalary);
        incomePErecovered = v.findViewById(R.id.incomePErecovered);
        incomePEprovidentfund = v.findViewById(R.id.incomePEprovidentfund);
        incomePEtaxpaidoutside = v.findViewById(R.id.incomePEtaxpaidoutside);

        internalform = v.findViewById(R.id.btnannexure3);
        internalform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LOPandSO();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayoutContainer, fragment).commit();

            }
        });
//        Log.d("k","hellooo");
//        System.out.println(incomeHPLetOut.getText()+"kjfndslkf");
//        incomeHPLetOut.addTextChangedListener(this);
//        incomeHPselfoccupied.addTextChangedListener(this);
//        incomeHPotherincome.addTextChangedListener(this);
//        incomePEsalary.addTextChangedListener(this);
//        incomePErecovered.addTextChangedListener(this);
//        incomePEprovidentfund.addTextChangedListener(this);
//        incomePEtaxpaidoutside.addTextChangedListener(this);
        return v;
    }
}

