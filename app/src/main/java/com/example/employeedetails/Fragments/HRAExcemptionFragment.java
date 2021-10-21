package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
Button btnprev;
Button btnnext;
ViewPager viewPager;
RecyclerView recyclerView;
HRAviewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_hra_excemption, container, false);

//        btnnext=v.findViewById(R.id.HRAnext);
//        btnprev=v.findViewById(R.id.HRAprevious);
        viewPager=getParentFragment().getView().findViewById(R.id.viewpager);

        //displaying all HRAItems
        recyclerView=v.findViewById(R.id.HRArecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HRAitemsAdapter adapter=new HRAitemsAdapter(getContext());
        recyclerView.setAdapter(adapter);
//        System.out.println("hello");
        viewModel= ViewModelProviders.of(getActivity()).get(HRAviewModel.class);
//        adapter.setall();
       viewModel.getHRAItemAll().observe(getActivity(), words -> {
            // Update the cached copy of the words in the adapter.
            adapter.setList(words);
        });
//        btnprev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(0,true);
//            }
//        });
//        btnnext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(2,true);
//            }
//        });
//        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(getActivity(),HRAItemActivity.class);
//                getActivity().startActivity(i);
//            }
//        });
        return v;
    }

}