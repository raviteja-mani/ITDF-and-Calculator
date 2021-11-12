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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.employeedetails.Adapters.DeductionsAdapter;
import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.MySession;
import com.example.employeedetails.TaxCalculator;
import com.example.employeedetails.ViewModels.HPviewModel;
import com.example.employeedetails.ViewModels.PESviewModel;
import com.example.employeedetails.R;

public class CalculatorFragment extends Fragment implements View.OnClickListener, TaxCalculator {
    TextView exeption10Dis;
    ImageView exeption10butt;

    TextView incomefromprevDis;
    ImageView incomefromprevbut;

    TextView incomefromhousDis;
    ImageView incomefromhousbut;

    TextView incomefromothdis;
    ImageView incomefromothbutt;

    TextView incomefrom80ccdis;
    ImageView incomefrmo80ccbutt;

    TextView deductChapterDis;
    ImageView deductChapterbutt;

    PESviewModel viewmodel;
    PESclass PESitem;

    HPviewModel viewmodelHP;
    HouseProperty HPitem;
    MySession session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        session=new MySession(getContext());
        getActivity().setTitle("Calculator");
        exeption10Dis = v.findViewById(R.id.exeption10Dis);
        exeption10butt = v.findViewById(R.id.execption10but);

        incomefromprevDis = v.findViewById(R.id.incomefromprevDis);
        incomefromprevbut = v.findViewById(R.id.incomefromprevbut);

        incomefromhousDis = v.findViewById(R.id.incomefromhousDis);
        incomefromhousbut = v.findViewById(R.id.incomefromhousbut);
        incomefromothbutt = v.findViewById(R.id.incomefromothbutt);

        incomefromothdis=v.findViewById(R.id.incomefromothdis);

        incomefrom80ccdis=v.findViewById(R.id.incomefrom80ccdis);
        incomefrmo80ccbutt=v.findViewById(R.id.incomefrmo80ccbutt);
        incomefrom80ccdis.setText(String.valueOf(session.getTotalDeductions()));
        exeption10Dis.setText(String.valueOf(session.getExemptions()));

        incomefromothdis.setText(String.valueOf(session.getTotalOtherIncome()));

        viewmodel = ViewModelProviders.of(getActivity()).get(PESviewModel.class);
        viewmodel.getPES().observe(getActivity(), data -> {
            PESitem = data;
            session.setPes(PESitem);
//            incomefromprevDis.setText(String.valueOf(data.getProvfund()));

        });
        viewmodelHP = ViewModelProviders.of(getActivity()).get(HPviewModel.class);
        viewmodelHP.getPES().observe(getActivity(), data -> {
            HPitem = data;
            session.setHouseProperty(HPitem);
//            incomefromhousDis.setText(String.valueOf(data.getTotalIncomefromHP()));
        });
        incomefrmo80ccbutt.setOnClickListener(this);
        incomefromhousbut.setOnClickListener(this);
        incomefromprevbut.setOnClickListener(this);
        exeption10butt.setOnClickListener(this);
        incomefromothbutt.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment;
    switch(v.getId()){
        case R.id.execption10but:
            fragment=new ExeptionUS10Fragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack("exception10").replace(R.id.frameLayoutContainer,fragment).commit();
            break;
        case R.id.incomefromprevbut:
            Bundle b = new Bundle();
            b.putSerializable("PESitem", PESitem);
            fragment = new IncomeFromPESFragment();
            fragment.setArguments(b);
//            FragmentManager managers = getActivity().getSupportFragmentManager();
            manager.beginTransaction().addToBackStack("incomefrompes").replace(R.id.frameLayoutContainer, fragment).commit();
            break;
        case R.id.incomefromhousbut:
            Bundle bun = new Bundle();
            bun.putSerializable("HP", HPitem);
            fragment = new IncomeFromHPFragment();
            fragment.setArguments(bun);

            manager.beginTransaction().addToBackStack("incomefromhp").replace(R.id.frameLayoutContainer, fragment).commit();
            break;
        case R.id.incomefromothbutt:
            fragment=new IncomeFromOSFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
            Bundle bb=new Bundle();
            bb.putSerializable("other income",session.getOtherIncome());
            fragment.setArguments(bb);
            manager.beginTransaction().addToBackStack("other sources").replace(R.id.frameLayoutContainer,fragment).commit();
            break;
        case R.id.incomefrmo80ccbutt:
            fragment=new DeductionsFragment();
//            FragmentManager manager=getActivity().getSupportFragmentManager();
            manager.beginTransaction().addToBackStack("deductions").replace(R.id.frameLayoutContainer,fragment).commit();
            break;


    }
    }

    @Override
    public long calculateTaxableIncome() {
        return 0;
    }

    @Override
    public long calculateIncomeTaxPayable() {
        return 0;
    }

    @Override
    public long calculateHealth() {
        return 0;
    }

    @Override
    public long calculateSurcharge() {
        return 0;
    }
}

