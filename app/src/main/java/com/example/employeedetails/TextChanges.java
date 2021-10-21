package com.example.employeedetails;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ViewModels.HPviewModel;

public class TextChanges implements TextWatcher {
    HouseProperty item;
    HPviewModel viewmodel;
    int i;
    public TextChanges(HouseProperty hp, HPviewModel viewmodel,int i) {
        this.item = hp;
        this.viewmodel = viewmodel;
        this.i=i;
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        long x=0;
        if(String.valueOf(s).equals(""))
            x=0;
        else
            x=Long.parseLong(String.valueOf(s));
    switch(i){
        case 1:
            item.setIncome_from_SOP(x);
            break;
        case 2:
            item.setAnnual_letable_value(x);
            break;
        case 3:
            item.setMunicipal_tax(x);
            break;
        case 4:
            item.setInteret_on_houseLoan(x);
            break;

    }
    viewmodel.update(item);
    }
}
