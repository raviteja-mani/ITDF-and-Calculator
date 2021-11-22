package com.example.employeedetails.Fragments.Calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.Adapters.DeductionsAdapter;
import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import java.util.ArrayList;

public class DeductionsFragment extends Fragment {


    DeductionsAdapter deductionsAdapter;
    RecyclerView recyclerView;
    Button backbutton;
    CalculatorViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_deductions, container, false);
        //        deductions fragment
        viewModel = new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        ArrayList<DeductionType> deductions = (ArrayList<DeductionType>) (getArguments().getSerializable("deductions"));
        recyclerView = v.findViewById(R.id.deduction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deductionsAdapter = new DeductionsAdapter(getActivity(), deductions,false);
        recyclerView.setAdapter(deductionsAdapter);
        backbutton = v.findViewById(R.id.deduction_backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CalculatorFragment fragment=new CalculatorFragment();
                viewModel.setDeductions(deductionsAdapter.getDeductionArray());
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}
