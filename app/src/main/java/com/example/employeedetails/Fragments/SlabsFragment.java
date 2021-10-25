package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.R;

public class SlabsFragment extends Fragment {
Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_slabs, container, false);
        button=v.findViewById(R.id.okbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
//                FragmentTransaction transaction=manager.beginTransaction();
                manager.popBackStack();

            }
        });
        return v;
    }
}