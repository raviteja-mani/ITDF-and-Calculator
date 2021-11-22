package com.example.employeedetails.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.OtherDeductions;
import com.example.employeedetails.ModalClasses.Otherimcome;
import com.example.employeedetails.ModalClasses.PESclass;

import java.util.ArrayList;

public class CalculatorViewModel extends ViewModel {

    private MutableLiveData<PESclass> pesItem=new MutableLiveData<>();
    private MutableLiveData<HouseProperty> houseProperty=new MutableLiveData<>();
    private MutableLiveData<Otherimcome> otherIncome=new MutableLiveData<>();
    private MutableLiveData<Exemptions> exemptions=new MutableLiveData<>();
    private MutableLiveData<ArrayList<DeductionType>> deductions=new MutableLiveData<>();
    private MutableLiveData<Long> grossSalary=new MutableLiveData<>();
    private MutableLiveData<ArrayList<OtherDeductions>> otherDeduction=new MutableLiveData<>();
    public void setHouseProperty(HouseProperty h){
        houseProperty.setValue(h);
    }
    public MutableLiveData<HouseProperty> getHouseProperty(){
        if(houseProperty.getValue()==null) setHouseProperty(new HouseProperty(0,0,0,0));
        return houseProperty;
    }

    public void setPesItem(PESclass pes){
        pesItem.setValue(pes);
    }
    public MutableLiveData<PESclass> getPesItem(){
        if(pesItem.getValue()==null) setPesItem(new PESclass(0,0,0,0,0));
        return pesItem;
    }

    public MutableLiveData<Otherimcome> getOtherIncome(){
        if(otherIncome.getValue()==null) setOtherIncomeItem(new Otherimcome(0,0));
        return otherIncome;
    }
    public void setOtherIncomeItem(Otherimcome oi){
        otherIncome.setValue(oi);
    }

    public MutableLiveData<Exemptions> getExemptions(){
        if(exemptions.getValue()==null) setExemptions(new Exemptions(0,0,0,0,"Yes"));
        return exemptions;
    }
    public void setExemptions(Exemptions ex){
        exemptions.setValue(ex);
    }

    public MutableLiveData<ArrayList<DeductionType>> getDeductions(){
//        if(deductions.getValue()==null) setDeductions(new Exemptions(0,0,0,0,"Yes"));
        return deductions;
    }
    public void setDeductions(ArrayList<DeductionType> ex){
        deductions.setValue(ex);
    }

    public MutableLiveData<Long> getGrossSalary(){
        return grossSalary;
    }
    public void setGrossSalary(Long val){
        grossSalary.setValue(val);
    }

    public MutableLiveData<ArrayList<OtherDeductions>> getOtherDeductions() {
        return otherDeduction;
    }

    public void setOtherDeductions(ArrayList<OtherDeductions> otherDeductions) {
        otherDeduction.setValue(otherDeductions);
    }
}
