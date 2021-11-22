package com.example.employeedetails.Fragments.ITDF;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.Adapters.DeductionsAdapter;
import com.example.employeedetails.Adapters.OtherDeductionsAdapter;
import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.ModalClasses.OtherDeductions;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import java.util.ArrayList;


public class InvestmentsFragment extends Fragment {
    DeductionsAdapter deductionsAdapter;
    OtherDeductionsAdapter deductionsAdapter2;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    Button backbutton;
    CalculatorViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_investments, container, false);
        Generalfunctions gf=new Generalfunctions(getContext());
        viewModel = new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        ArrayList<DeductionType> deductions = gf.getITDFDeductionArray();
        recyclerView = v.findViewById(R.id.itdf_deduction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deductionsAdapter = new DeductionsAdapter(getActivity(), deductions,true);
        recyclerView.setAdapter(deductionsAdapter);

        ArrayList<OtherDeductions> deductions2=gf.getITDFOtherDeductions();
        recyclerView=v.findViewById(R.id.itdf_other_deduction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deductionsAdapter2=new OtherDeductionsAdapter(getActivity(),deductions2,true);
        recyclerView.setAdapter(deductionsAdapter2);
//        backbutton = v.findViewById(R.id.deduction_backbutton);
        return v;
    }
}