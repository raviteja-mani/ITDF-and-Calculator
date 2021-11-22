package com.example.employeedetails.Fragments.ITDF;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.Adapters.HRAitemsAdapter;
import com.example.employeedetails.ViewModels.HRAviewModel;
import com.example.employeedetails.R;


public class HRAExcemptionFragment extends Fragment {

ViewPager viewPager;
RecyclerView recyclerView;
HRAviewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_hra_excemption, container, false);


        viewPager=getParentFragment().getView().findViewById(R.id.viewpager);

        recyclerView=v.findViewById(R.id.HRArecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        HRAitemsAdapter adapter=new HRAitemsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(getActivity()).get(HRAviewModel.class);

       viewModel.getHRAItemAll().observe(getActivity(), words -> {

            adapter.setList(words);
        });

        return v;
    }

}