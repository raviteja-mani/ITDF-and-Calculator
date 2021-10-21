package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.ViewModels.HPviewModel;
import com.example.employeedetails.ViewModels.PESviewModel;
import com.example.employeedetails.R;

public class CalculatorFragment extends Fragment {
    TextView exeption10Dis;
    Button exeption10butt;

    TextView incomefromprevDis;
    Button incomefromprevbut;

    TextView incomefromhousDis;
    Button incomefromhousbut;

    TextView incomefromothdis;
    Button incomefromothbutt;

    TextView incomefrom80ccdis;
    Button incomefrmo80ccbutt;

    TextView deductChapterDis;
    Button deductChapterbutt;

    PESviewModel viewmodel;
    PESclass PESitem;

    HPviewModel viewmodelHP;
    HouseProperty HPitem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_calculator, container, false);
        exeption10Dis=v.findViewById(R.id.execption10but);
        exeption10butt=v.findViewById(R.id.execption10but);

        incomefromprevDis=v.findViewById(R.id.incomefromprevDis);
        incomefromprevbut=v.findViewById(R.id.incomefromprevbut);

        incomefromhousDis=v.findViewById(R.id.incomefromhousDis);
        incomefromhousbut=v.findViewById(R.id.incomefromhousbut);

        viewmodel= ViewModelProviders.of(getActivity()).get(PESviewModel.class);
        viewmodel.getPES().observe(getActivity(), data -> {
            PESitem=data;

            incomefromprevDis.setText(String.valueOf(data.getProvfund()));

        });

  viewmodelHP=ViewModelProviders.of(getActivity()).get(HPviewModel.class);
  viewmodelHP.getPES().observe(getActivity(),data->{
    HPitem=data;
    incomefromhousDis.setText(String.valueOf(data.getTotalIncomefromHP()));
   });
        incomefromhousbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putSerializable("HP",HPitem);
                Fragment fragment=new IncomeFromHPFragment();
                fragment.setArguments(b);
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.beginTransaction().addToBackStack("incomefromhp").replace(R.id.frameLayoutContainer,fragment).commit();

            }
        });
        incomefromprevbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putSerializable("PESitem",PESitem);
                Fragment frag=new IncomeFromPESFragment();
                frag.setArguments(b);
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.beginTransaction().addToBackStack("incomefrompes").replace(R.id.frameLayoutContainer,frag).commit();

            }
        });
        exeption10butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.addToBackStack("exception10").replace(R.id.frameLayoutContainer,new ExeptionUS10Fragment()).commit();

            }
        });

        return v;
    }

}