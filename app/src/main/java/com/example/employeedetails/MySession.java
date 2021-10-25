package com.example.employeedetails;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.employeedetails.ModalClasses.HRAItem;
import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.Otherimcome;
import com.example.employeedetails.ModalClasses.PESclass;

import java.util.ArrayList;
import java.util.List;

public class MySession {
    List<HRAItem> listOfHRA=new ArrayList<>();
    Otherimcome otherIncome=null;
    PESclass pes=null;
    HouseProperty houseProperty=null;
    SharedPreferences sharedprefs;
    public MySession(Context context) {
        sharedprefs=context.getSharedPreferences("otherIncome", Activity.MODE_PRIVATE);
        otherIncome=new Otherimcome();
        otherIncome.setInterestOnSavings(sharedprefs.getInt("Interest on Savings",0));
        otherIncome.setOtherIncome(sharedprefs.getInt("other Income",0));
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
        if(otherIncome==null){
            otherIncome=new Otherimcome();
            otherIncome.setInterestOnSavings(sharedprefs.getInt("Interest on Savings",0));
            otherIncome.setOtherIncome(sharedprefs.getInt("other Income",0));
        }
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
}
