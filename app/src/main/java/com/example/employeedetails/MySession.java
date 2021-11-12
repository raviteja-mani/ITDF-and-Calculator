package com.example.employeedetails;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.ModalClasses.HRAItem;
import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.Otherimcome;
import com.example.employeedetails.ModalClasses.PESclass;

import java.util.ArrayList;
import java.util.List;
//completely for calculator
public class MySession {
    List<HRAItem> listOfHRA=new ArrayList<>();
    ArrayList<DeductionType> deductions;
    Otherimcome otherIncome=null;
    PESclass pes=null;
    HouseProperty houseProperty=null;
    SharedPreferences sharedprefs;
    Exemptions exemptions;
    public MySession(Context context) {
        sharedprefs=context.getSharedPreferences("employeeDetails", Activity.MODE_PRIVATE);
//        otherIncome=new Otherimcome();
//        otherIncome.setInterestOnSavings(sharedprefs.getInt("InterestOnSavings",0));
//        otherIncome.setOtherIncome(sharedprefs.getInt("otherIncome",0));
//        deductionSharedprefs=context.getSharedPreferences("deductions",Activity.MODE_PRIVATE);
        Generalfunctions funcs=new Generalfunctions(context);
        deductions=funcs.getDeductionArray();
        exemptions=funcs.getExemptions();

            otherIncome=new Otherimcome();
            otherIncome.setInterestOnSavings(sharedprefs.getInt("InterestOnSavings",0));
            otherIncome.setOtherIncome(sharedprefs.getInt("otherIncome",0));

    }
    public MySession(List<HRAItem> listOfHRA, Otherimcome otherIncome, PESclass pes, HouseProperty houseProperty) {
        this.listOfHRA = listOfHRA;
        this.otherIncome = otherIncome;
        this.pes = pes;
        this.houseProperty = houseProperty;
    }

    public void setListOfHRA(List<HRAItem> listOfHRA) {
        this.listOfHRA = listOfHRA;
    }

    public void setOtherIncome(Otherimcome otherIncome) {
        this.otherIncome = otherIncome;
    }

    public void setPes(PESclass pes) {
        this.pes = pes;
    }

    public void setHouseProperty(HouseProperty houseProperty) {
        this.houseProperty = houseProperty;
    }

    public List<HRAItem> getListOfHRA() {
        return listOfHRA;
    }

    public Otherimcome getOtherIncome() {

        return otherIncome;
    }

    public PESclass getPes() {
        return pes;
    }

    public HouseProperty getHouseProperty() {
        return houseProperty;
    }
    public int getTotalOtherIncome(){
        Otherimcome o=getOtherIncome();
        return o.getOtherIncome()+o.getInterestOnSavings();
    }
    public int getTotalDeductions(){
        int total=0;
        for(DeductionType d:deductions){
            total+=d.getDeclared();
        }
        if(total>150000)
        return 150000;
        else return total;
    }
    public int getExemptions(){
        return exemptions.getTotalExemptions();
    }
}
