package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.Fragments.DeclarationFragment;
import com.example.employeedetails.R;

public class GuideLinesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_guide_lines, container, false);
        Button btn=(Button) v.findViewById(R.id.nextDeclaration);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Declaration").replace(R.id.frameLayoutContainer,new DeclarationFragment()).commit();
//                MenuItem item=v.findViewById(R.id.nav_declaration);
//                item.setChecked(true);
            }
        });
        return v;
    }
}