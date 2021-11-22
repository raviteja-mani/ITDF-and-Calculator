package com.example.employeedetails.Fragments.Calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.Adapters.DeductionsAdapter;
import com.example.employeedetails.Adapters.OtherDeductionsAdapter;
import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.OtherDeductions;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import java.util.ArrayList;

public class IncomeFromCCsFragment extends Fragment {


OtherDeductionsAdapter deductionsAdapter;
    RecyclerView recyclerView;
    Button backbutton;
    CalculatorViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_income_from_ccs, container, false);
        viewModel=new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        ArrayList<OtherDeductions> deductions=(ArrayList<OtherDeductions>)(getArguments().getSerializable("otherDeductions"));
        recyclerView=v.findViewById(R.id.other_deduction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deductionsAdapter=new OtherDeductionsAdapter(getActivity(),deductions,false);
        recyclerView.setAdapter(deductionsAdapter);
        backbutton=v.findViewById(R.id.other_deduction_backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CalculatorFragment fragment=new CalculatorFragment();
                viewModel.setOtherDeductions(deductionsAdapter.getDeductionArray());
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}