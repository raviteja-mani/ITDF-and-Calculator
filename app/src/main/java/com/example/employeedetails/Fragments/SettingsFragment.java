package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.employeedetails.Fragments.ITDF.DeclarationFragment;
import com.example.employeedetails.R;

public class SettingsFragment extends Fragment {
    CardView updateRegime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_settings, container, false);
        getActivity().setTitle("Settings");
        updateRegime=v.findViewById(R.id.updateCard);
        updateRegime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new DeclarationFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                Bundle bundle=new Bundle();
                bundle.putString("fromMain","No");
                fragment.setArguments(bundle);
                manager.beginTransaction().addToBackStack("fromSettings").replace(R.id.frameLayoutContainer,fragment).commit();
            }
        });
        return v;
    }
}