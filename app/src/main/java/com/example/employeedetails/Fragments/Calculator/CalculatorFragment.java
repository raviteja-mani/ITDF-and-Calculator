package com.example.employeedetails.Fragments.Calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.OtherDeductions;
import com.example.employeedetails.ModalClasses.Otherimcome;
import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.MySession;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment implements View.OnClickListener {
    EditText grosssalary;
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

    PESclass PESitem;
    HouseProperty HPitem;
    Otherimcome otherIncome;
    Exemptions exemptions;
    ArrayList<DeductionType> deductions;
    ArrayList<OtherDeductions> otherDeductions;
    CalculatorViewModel viewmodelHP;
//    long grossSalary;

    MySession session;

    TextView PayableNewtax;
    TextView incomeTaxPayableNew;
    TextView surchargeNew;
    TextView healthNew;
    TextView taxLiable;

    TextView taxPayable;
    TextView incomeTaxPayable;
    TextView surcharges;
    TextView health;
    TextView taxLiableNew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        session=new MySession(getActivity());
        getActivity().setTitle("Calculator");
        grosssalary=v.findViewById(R.id.grosssalary);
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

        deductChapterbutt=v.findViewById(R.id.deductChapterbutt);
        deductChapterDis=v.findViewById(R.id.deductChapterDis);
        incomefrom80ccdis.setText(String.valueOf(session.getTotalDeductions()));
        exeption10Dis.setText(String.valueOf(session.getTotalExemptions()));

        incomefromothdis.setText(String.valueOf(session.getTotalOtherIncome()));


        PayableNewtax=v.findViewById(R.id.PayableNewtax);
        incomeTaxPayableNew=v.findViewById(R.id.incomeTaxPayableNew);
        surchargeNew=v.findViewById(R.id.surchargeNew);
        healthNew=v.findViewById(R.id.healthNew);
        taxLiable=v.findViewById(R.id.taxLiable);

        taxPayable=v.findViewById(R.id.taxPayable);
        incomeTaxPayable=v.findViewById(R.id.incomeTaxPayable);
        surcharges=v.findViewById(R.id.surcharge);
        health=v.findViewById(R.id.health);
        taxLiableNew=v.findViewById(R.id.taxLiableNew);
//        viewmodel = ViewModelProviders.of(getActivity()).get(PESviewModel.class);
        viewmodelHP = new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        viewmodelHP.setPesItem(session.getPes());
        viewmodelHP.setOtherIncomeItem(session.getOtherIncome());
        viewmodelHP.setHouseProperty(session.getHouseProperty());
        viewmodelHP.setExemptions(session.getExemptions());
        viewmodelHP.setDeductions(session.getDeductions());
        viewmodelHP.setGrossSalary(session.getGrossSalary());
        viewmodelHP.setOtherDeductions(session.getOtherDeductions());
        grosssalary.setText(String.valueOf(session.getGrossSalary()));
        grosssalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(String.valueOf(s).equals("")||!(String.valueOf(s).matches("[0-9]*"))) {
                    viewmodelHP.setGrossSalary((long)0);
                }
                else  viewmodelHP.setGrossSalary(Long.parseLong(String.valueOf(s)));
            }
        });

        viewmodelHP.getGrossSalary().observe(getActivity(),data->{
//            grossSalary=data;
            session.setGrossSalary(data);
//            grosssalary.setText(String.valueOf(data));
            updateTaxCalculation();
        });
        viewmodelHP.getOtherDeductions().observe(getActivity(),data->{
            otherDeductions=data;
            session.setOtherDeductions(data);
            deductChapterDis.setText(String.valueOf(session.getTotalOtherDeductions()));
            updateTaxCalculation();
        });
        viewmodelHP.getExemptions().observe(getActivity(),data->{
            exemptions=data;
            session.setExemptions(data);
            exeption10Dis.setText(String.valueOf(data.getTotalExemptions()));
            updateTaxCalculation();
        });
        viewmodelHP.getPesItem().observe(getActivity(), data -> {
            PESitem = data;
            session.setPes(PESitem);
            incomefromprevDis.setText(String.valueOf(data.getTotalPES()));
            updateTaxCalculation();
        });

        viewmodelHP.getHouseProperty().observe(getActivity(), data -> {
            HPitem = data;
            session.setHouseProperty(HPitem);
            incomefromhousDis.setText(String.valueOf(data.getTotalIncomefromHP()));
            updateTaxCalculation();
        });
        viewmodelHP.getOtherIncome().observe(getActivity(),data->{
            otherIncome=data;
            session.setOtherIncome(data);
            incomefromothdis.setText(String.valueOf(data.getTotalOtherIncome()));
            updateTaxCalculation();
        });
        viewmodelHP.getDeductions().observe(getActivity(),data->{
            deductions=data;
            session.setDeductions(data);
            incomefrom80ccdis.setText(String.valueOf(session.getTotalDeductions()));
            updateTaxCalculation();
        });
        incomefrmo80ccbutt.setOnClickListener(this);
        incomefromhousbut.setOnClickListener(this);
        incomefromprevbut.setOnClickListener(this);
        exeption10butt.setOnClickListener(this);
        incomefromothbutt.setOnClickListener(this);
        deductChapterbutt.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragment;
    switch(v.getId()){
        case R.id.execption10but:
            fragment=new ExeptionUS10Fragment();
            Bundle exbundle=new Bundle();
            exbundle.putSerializable("exeption10", exemptions);
            fragment.setArguments(exbundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack("exception10").replace(R.id.frameLayoutContainer,fragment).commit();
            break;
        case R.id.incomefromprevbut:
            Bundle b = new Bundle();
            b.putSerializable("PESitem", PESitem);
            fragment = new IncomeFromPESFragment();
            fragment.setArguments(b);
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
            Bundle bb=new Bundle();
            bb.putSerializable("other income",session.getOtherIncome());
            fragment.setArguments(bb);
            manager.beginTransaction().addToBackStack("other sources").replace(R.id.frameLayoutContainer,fragment).commit();
            break;
        case R.id.incomefrmo80ccbutt:
            fragment=new DeductionsFragment();
            Bundle dedBundle=new Bundle();
            dedBundle.putSerializable("deductions",session.getDeductions());
            fragment.setArguments(dedBundle);
            manager.beginTransaction().addToBackStack("deductions").replace(R.id.frameLayoutContainer,fragment).commit();
            break;
        case R.id.deductChapterbutt:
            fragment=new IncomeFromCCsFragment();
            Bundle odedBundle=new Bundle();
            odedBundle.putSerializable("otherDeductions",session.getOtherDeductions());
            fragment.setArguments(odedBundle);
            manager.beginTransaction().addToBackStack("otherdedductions").replace(R.id.frameLayoutContainer,fragment).commit();
            break;
    }
    }

    public void loadNewTaxRegime(){
        long gross=session.getGrossSalary();
        long taxable_income = 0;
        long tax_inc = gross;
        if (tax_inc<=0)
            taxable_income = 0;
        else
            taxable_income = tax_inc;

        //settaxpayable
        PayableNewtax.setText(String.valueOf(taxable_income));
        long tax_payable = 0;
//        taxable_income = (taxable_income);

        if (taxable_income<=250000) {
            tax_payable = 0;
        } else if (taxable_income>250000 && taxable_income<=500000) {
            tax_payable = 0;
        } else if (taxable_income>500000 && taxable_income<=750000) {
            tax_payable = (long)((taxable_income-500000)*0.10 + 12500); // 25000+12500 = 37500
        } else if (taxable_income>750000 && taxable_income<=1000000) {
            tax_payable = (long)((taxable_income-750000)*0.15 + 37500); // 37500+37500 = 75000
        } else if (taxable_income>1000000 && taxable_income<=1250000) {
            tax_payable = (long)((taxable_income-1000000)*0.20 + 75000); // 50000+75000 = 125000
        } else if (taxable_income>1250000 && taxable_income<=1500000) {
            tax_payable = (long)((taxable_income-1250000)*0.25 + 125000); // 62500+125000 = 187500
        } else if (taxable_income>1500000) {
            tax_payable = (long)((taxable_income-1500000)*0.30 + 187500);
        }
        //settaxPayableIncome
        incomeTaxPayableNew.setText(String.valueOf(tax_payable));
        long tax_pay = tax_payable;
        // Surcharge
        long surcharge = 0;
        if (taxable_income > 5000000 && taxable_income <= 10000000) {
            long surcharge_1 = (long)(tax_pay * 0.10);
            long surcharge_2 = (long)((taxable_income-5000000) * 0.7);
            if (surcharge_1 < surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        } else if(taxable_income > 10000000 && taxable_income <= 20000000) {
            long surcharge_1 = (long)(tax_pay * 0.15);
            long surcharge_2 = (long)((taxable_income-10000000) * 0.7) + 273750;
            if (surcharge_1<surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        } else if(taxable_income > 20000000 && taxable_income <= 50000000) {
            long surcharge_1 =(long)(tax_pay * 0.25);
            long surcharge_2 = (long)(((taxable_income-20000000) * 0.7) + 860625);
            if (surcharge_1<surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        } else if(taxable_income > 50000000) {
            long surcharge_1 = (long)(tax_pay * 0.37);
            long surcharge_2 = (long)(((taxable_income-50000000) * 0.7) + 3684375);
            if (surcharge_1<surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        }
        surcharge = Math.round(surcharge);
        //setSurcharge
        surchargeNew.setText(String.valueOf(surcharge));
       long edu_cess = Math.round((tax_pay+surcharge)*0.04);
        edu_cess = Math.round(edu_cess);

        long tax_liability = Math.round(tax_pay+surcharge+edu_cess);
        //setHealth and education cess
        healthNew.setText(String.valueOf(edu_cess));
        //set tax_liability
        taxLiableNew.setText(String.valueOf(tax_liability));

    }
    public void updateTaxCalculation(){
        loadNewTaxRegime();
        long deductionss=session.getTotalDeductions();
        long totalHRAExemptions=session.getTotalExemptions();
        long gs=session.getGrossSalary();
        long stddtions=50000;
        long otherIncome=session.getTotalOtherIncome();
        long incomeFromHouse=(long)session.getHouseProperty().getTotalIncomefromHP();
        long fromPES=session.getPes().calculated();
        long otherDeductionss=session.getTotalOtherDeductions();
        long taxable_income=gs-totalHRAExemptions-stddtions+otherIncome+incomeFromHouse+fromPES-deductionss-otherDeductionss;
        if(taxable_income<0)
            taxable_income=0;
        taxPayable.setText(String.valueOf(taxable_income));
        long tax_payable = 0;
//        taxable_income = (taxable_income);

        if (taxable_income<=250000) {
            tax_payable = 0;
        } else if (taxable_income>250000 && taxable_income<=500000) {
            tax_payable = 0;
        } else if (taxable_income>500000 && taxable_income<=1000000) {
            tax_payable = (long)((taxable_income-500000)*0.20 + 12500);
        } else if (taxable_income>1000000) {
            tax_payable = (long)((taxable_income-1000000)*0.30 + 112500);
        }
        //settaxPayableIncome
        incomeTaxPayable.setText(String.valueOf(tax_payable));
        long tax_pay = tax_payable;
        // Surcharge
        long surcharge = 0;
        if (taxable_income > 5000000 && taxable_income <= 10000000) {
            long surcharge_1 = (long)(tax_pay * 0.10);
            long surcharge_2 = (long)((taxable_income-5000000) * 0.7);
            if (surcharge_1 < surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        } else if(taxable_income > 10000000 && taxable_income <= 20000000) {
            long surcharge_1 = (long)(tax_pay * 0.15);
            long surcharge_2 = (long)((taxable_income-10000000) * 0.7) + 273750;
            if (surcharge_1<surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        } else if(taxable_income > 20000000 && taxable_income <= 50000000) {
            long surcharge_1 =(long)(tax_pay * 0.25);
            long surcharge_2 = (long)(((taxable_income-20000000) * 0.7) + 860625);
            if (surcharge_1<surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        } else if(taxable_income > 50000000) {
            long surcharge_1 = (long)(tax_pay * 0.37);
            long surcharge_2 = (long)(((taxable_income-50000000) * 0.7) + 3684375);
            if (surcharge_1<surcharge_2)
                surcharge = surcharge_1;
            else
                surcharge = surcharge_2;
        }
        surcharge = Math.round(surcharge);
        //setSurcharge
        surcharges.setText(String.valueOf(surcharge));
        long edu_cess = Math.round((tax_pay+surcharge)*0.04);
        edu_cess = Math.round(edu_cess);

        long tax_liability = Math.round(tax_pay+surcharge+edu_cess);
        //setHealth and education cess
        health.setText(String.valueOf(edu_cess));
        //set tax_liability
        taxLiable.setText(String.valueOf(tax_liability));


    }


}

