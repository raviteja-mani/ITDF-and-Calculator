package com.example.employeedetails.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.R;
import com.example.employeedetails.TextChanges;
import com.example.employeedetails.ViewModels.HPviewModel;

public class IncomeFromHPFragment extends Fragment {

    EditText income_selfOccupied;
    EditText income_letValue;
    EditText income_municipal;
    EditText income_HouseLoan;
    TextView HP_c_netValue;
    TextView HP_income_from_letoutProp;
    TextView HP_total_incORloss;
    TextView HP_deductions30perAnual;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragement_income_from_hp);
////        income_selfOccupied=findViewById(R.id.HP_selfOccupied);
////        income_letValue=findViewById(R.id.HP_a_letableVR);
////        income_municipal=findViewById(R.id.HP_b_municipalTP);
////        income_HouseLoan=findViewById(R.id.HP_InterestHL);
////        HP_c_netValue=findViewById(R.id.HP_c_netValue);
////        HP_income_from_letoutProp=findViewById(R.id.HP_income_from_letoutProp);
////        HP_total_incORloss=findViewById(R.id.HP_total_incORloss);
////        HP_deductions30perAnual=findViewById(R.id.HP_deductions30perAnual);
////
////        Intent i=getIntent();
////        HouseProperty item=(HouseProperty) i.getSerializableExtra("HP");
////        HPviewModel viewmodel= ViewModelProviders.of(this).get(HPviewModel.class);
////        income_selfOccupied.setText(String.valueOf(item.getIncome_from_SOP()));
////        income_letValue.setText(String.valueOf(item.getAnnual_letable_value()));
////        income_municipal.setText(String.valueOf(item.getMunicipal_tax()));
////        income_HouseLoan.setText(String.valueOf(item.getInteret_on_houseLoan()));
////        income_selfOccupied.addTextChangedListener(
//////                new TextWatcher() {
//////            @Override
//////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//////
//////            }
//////
//////            @Override
//////            public void onTextChanged(CharSequence s, int start, int before, int count) {
//////
//////            }
//////
//////            @Override
//////            public void afterTextChanged(Editable s) {
//////                if(String.valueOf(s).equals(""))
//////                    item.setIncome_from_SOP(0);
//////                else
//////                    item.setIncome_from_SOP(Long.parseLong(String.valueOf(s)));
//////            viewmodel.update(item);
//////            }
//////        }
////      new TextChanges(item,viewmodel,1)
////        );
////
////        income_letValue.addTextChangedListener(
//////                new TextWatcher() {
//////            @Override
//////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//////
//////            }
//////
//////            @Override
//////            public void onTextChanged(CharSequence s, int start, int before, int count) {
//////
//////            }
//////
//////            @Override
//////            public void afterTextChanged(Editable s) {
//////                if(String.valueOf(s).equals(""))
//////                    item.setAnnual_letable_value(0);
//////                else
//////                    item.setAnnual_letable_value(Long.parseLong(String.valueOf(s)));
//////                viewmodel.update(item);
//////            }
//////        }
////       new TextChanges(item,viewmodel,2)
////        );
////
////        income_municipal.addTextChangedListener(
//////                new TextWatcher() {
//////            @Override
//////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//////
//////            }
//////
//////            @Override
//////            public void onTextChanged(CharSequence s, int start, int before, int count) {
//////
//////            }
//////
//////            @Override
//////            public void afterTextChanged(Editable s) {
//////                if(String.valueOf(s).equals(""))
//////                    item.setMunicipal_tax(0);
//////                else
//////                    item.setMunicipal_tax(Long.parseLong(String.valueOf(s)));
//////                viewmodel.update(item);
//////            }
//////        }
////       new TextChanges(item,viewmodel,3)
////        );
////
////        income_HouseLoan.addTextChangedListener(
//////                new TextWatcher() {
//////            @Override
//////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//////
//////            }
//////
//////            @Override
//////            public void onTextChanged(CharSequence s, int start, int before, int count) {
//////
//////            }
//////
//////            @Override
//////            public void afterTextChanged(Editable s) {
//////                if(String.valueOf(s).equals(""))
//////                    item.setInteret_on_houseLoan(0);
//////                else
//////                    item.setInteret_on_houseLoan(Long.parseLong(String.valueOf(s)));
//////                viewmodel.update(item);
//////            }
//////        }
////       new TextChanges(item,viewmodel,4)
////        );
////        viewmodel.getPES().observe(this,data->{
////            HP_c_netValue.setText(String.valueOf(data.getNetAnnualValue()));
////            HP_income_from_letoutProp.setText(String.valueOf(data.getIncomefromLetout()));
////            HP_total_incORloss.setText(String.valueOf(data.getTotalIncomefromHP()));
////            HP_deductions30perAnual.setText(String.valueOf(data.getStandartDeduction()));
////        });
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragement_income_from_hp, container, false);
        income_selfOccupied=v.findViewById(R.id.HP_selfOccupied);
        income_letValue=v.findViewById(R.id.HP_a_letableVR);
        income_municipal=v.findViewById(R.id.HP_b_municipalTP);
        income_HouseLoan=v.findViewById(R.id.HP_InterestHL);
        HP_c_netValue=v.findViewById(R.id.HP_c_netValue);
        HP_income_from_letoutProp=v.findViewById(R.id.HP_income_from_letoutProp);
        HP_total_incORloss=v.findViewById(R.id.HP_total_incORloss);
        HP_deductions30perAnual=v.findViewById(R.id.HP_deductions30perAnual);

        Bundle i=getArguments();
        HouseProperty item=(HouseProperty) i.getSerializable("HP");
        HPviewModel viewmodel= ViewModelProviders.of(this).get(HPviewModel.class);
        income_selfOccupied.setText(String.valueOf(item.getIncome_from_SOP()));
        income_letValue.setText(String.valueOf(item.getAnnual_letable_value()));
        income_municipal.setText(String.valueOf(item.getMunicipal_tax()));
        income_HouseLoan.setText(String.valueOf(item.getInteret_on_houseLoan()));
        income_selfOccupied.addTextChangedListener(
                new TextChanges(item,viewmodel,1)
        );

        income_letValue.addTextChangedListener(
                new TextChanges(item,viewmodel,2)
        );

        income_municipal.addTextChangedListener(
                new TextChanges(item,viewmodel,3)
        );

        income_HouseLoan.addTextChangedListener(
                new TextChanges(item,viewmodel,4)
        );
        viewmodel.getPES().observe(getActivity(),data->{
            HP_c_netValue.setText(String.valueOf(data.getNetAnnualValue()));
            HP_income_from_letoutProp.setText(String.valueOf(data.getIncomefromLetout()));
            HP_total_incORloss.setText(String.valueOf(data.getTotalIncomefromHP()));
            HP_deductions30perAnual.setText(String.valueOf(data.getStandartDeduction()));
        });

        return v;
    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
}