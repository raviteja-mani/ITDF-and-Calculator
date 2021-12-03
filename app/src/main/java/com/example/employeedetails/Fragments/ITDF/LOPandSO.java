package com.example.employeedetails.Fragments.ITDF;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.employeedetails.R;


public class LOPandSO extends Fragment {
LinearLayout self_occupied_hide;
LinearLayout coapplicant_hideout;
LinearLayout letout_hideout;
LinearLayout coapplicant_drawer;
LinearLayout self_occupied_drawer;
LinearLayout letout_drawer;
Button lopback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_l_o_pand_s_o, container, false);
        getActivity().setTitle("Annexure-3");
        lopback=v.findViewById(R.id.lopback);
        self_occupied_hide=v.findViewById(R.id.self_occupied_hide);
        coapplicant_hideout=v.findViewById(R.id.coapplicant_hideout);
        letout_hideout=v.findViewById(R.id.letout_hideout);

        coapplicant_drawer=v.findViewById(R.id.coapplicant_drawer);
        self_occupied_drawer=v.findViewById(R.id.self_occupied_drawer);
        letout_drawer=v.findViewById(R.id.letout_drawer);

        coapplicant_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(self_occupied_hide.getVisibility()==View.VISIBLE)
                    self_occupied_hide.setVisibility(View.GONE);
                if(letout_hideout.getVisibility()==View.VISIBLE) letout_hideout.setVisibility(View.GONE);
                if(coapplicant_hideout.getVisibility()==View.GONE) coapplicant_hideout.setVisibility(View.VISIBLE);

            }
        });
        self_occupied_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(self_occupied_hide.getVisibility()==View.GONE) self_occupied_hide.setVisibility(View.VISIBLE);
                if(letout_hideout.getVisibility()==View.VISIBLE) letout_hideout.setVisibility(View.GONE);
                if(coapplicant_hideout.getVisibility()==View.VISIBLE) coapplicant_hideout.setVisibility(View.GONE);

            }
        });
        letout_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(self_occupied_hide.getVisibility()==View.VISIBLE) self_occupied_hide.setVisibility(View.GONE);
                if(letout_hideout.getVisibility()==View.GONE) letout_hideout.setVisibility(View.VISIBLE);
                if(coapplicant_hideout.getVisibility()==View.VISIBLE) coapplicant_hideout.setVisibility(View.GONE);

            }
        });
        lopback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}