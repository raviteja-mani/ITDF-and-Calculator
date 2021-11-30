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
import android.widget.Button;
import android.widget.EditText;

import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

public class  IncomeFromPESFragment extends Fragment {
CalculatorViewModel viewmodel;
EditText PESrow1;
EditText PESrow2;
EditText PESrow3;
EditText PESrow4;
EditText PESrow5;
Button savebtn;
Button cancelbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_income_from_pes, container, false);
        Bundle intent=getArguments();

        PESclass item=(PESclass) intent.getSerializable("PESitem");
        PESrow1=v.findViewById(R.id.PESrow1);
        PESrow2=v.findViewById(R.id.PESrow2);
        PESrow3=v.findViewById(R.id.PESrow3);
        PESrow4=v.findViewById(R.id.PESrow4);
        PESrow5=v.findViewById(R.id.PESrow5);
        savebtn=v.findViewById(R.id.PESsaveBtn);
        cancelbtn=v.findViewById(R.id.PESCancel);
        PESrow1.setText(String.valueOf(item.getGsalary()));
        PESrow2.setText(String.valueOf(item.getExUSs()));
        PESrow3.setText(String.valueOf(item.getProfTax()));
        PESrow4.setText(String.valueOf(item.getProvfund()));
        PESrow5.setText(String.valueOf(item.getIncTax()));
        viewmodel= new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setGsalary((int)Integer.parseInt(String.valueOf(PESrow1.getText())));
                item.setExUSs((int)Integer.parseInt(String.valueOf(PESrow2.getText())));
                item.setIncTax((int)Integer.parseInt(String.valueOf(PESrow3.getText())));
                item.setProvfund((int)Integer.parseInt(String.valueOf(PESrow4.getText())));
                item.setIncTax((int)Integer.parseInt(String.valueOf(PESrow5.getText())));

                viewmodel.setPesItem(item);

                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}