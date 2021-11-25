package com.example.employeedetails;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.ModalClasses.PESclass;

public class TextChanges2 implements TextWatcher {
    Generalfunctions funcs;
    int i;
    String type;
    public TextChanges2(Generalfunctions funcs, int i,String type) {
        this.funcs = funcs;
        this.i = i;
        this.type=type;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        PESclass item=funcs.getPES(type);
        long x=0;
        if(String.valueOf(s).equals(""))
            x=0;
        else
            x=Long.parseLong(String.valueOf(s));
        switch(i){
            case 0:
                item.setGsalary(x);
                break;
            case 1:
                item.setProvfund(x);
                break;
            case 2:
                item.setIncTax(x);
                break;
            case 3:
                item.setProfTax(x);
                break;

        }
        funcs.setPES(item,type);
    }
}
