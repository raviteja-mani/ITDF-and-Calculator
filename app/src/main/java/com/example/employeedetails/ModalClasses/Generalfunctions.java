package com.example.employeedetails.ModalClasses;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;

public class Generalfunctions {
    public static String SHARED_PREFS="deductions";
    public static String[] deductionTitles={"Employee Provident Fund",
            "Voluntary Provident Fund",
            "Provident Fund contributed with previous employer",
            "Deduction under life insurance pension scheme (80CCC)",
            "Public Provident Fund",
            "Children Education",
            "National Savings Certificate",
            "Accrued NSC Interest",
            "Life Insurance premium paid",
            "Housing Loan principal repayment",
            "Sukanya Samriddi Account",
            "Approved super annuation"};
    SharedPreferences sharedPreferences;
    public static Context context;
    SharedPreferences.Editor prefsEditor;
    public Generalfunctions(Context context) {
        this.context=context;
        sharedPreferences=context.getSharedPreferences("employeeDetails",Context.MODE_PRIVATE);
        prefsEditor=sharedPreferences.edit();
    }

    public ArrayList<DeductionType> getDeductionArray(){
        Gson gson=new Gson();
         DeductionType[] arr=gson.fromJson(sharedPreferences.getString("deductions",gson.toJson(defaultDeductions())),
                 DeductionType[].class);
         ArrayList<DeductionType> a=new ArrayList<DeductionType>(Arrays.asList(arr));
         return a;
    }
    public void setDeductionArray(ArrayList<DeductionType> arr){

        Gson gson=new Gson();
        prefsEditor.putString("deductions",gson.toJson(arr));
        prefsEditor.commit();
    }
    public ArrayList<DeductionType> defaultDeductions(){
        ArrayList<DeductionType> arr=new ArrayList<>();
        for(String title:deductionTitles){
            arr.add(new DeductionType(title,0,0));
        }
        return arr;
    }
    public void setExemptions(Exemptions e){
        Gson gson=new Gson();
        prefsEditor.putString("exemptions",gson.toJson(e));
        prefsEditor.commit();
    }
    public Exemptions getExemptions(){
        Gson gson=new Gson();
        String s=sharedPreferences.getString("exemptions",null);
        if(s==null){
         return new Exemptions(0,0,0,0,"Yes");
        }
        else return gson.fromJson(s,Exemptions.class);
    }

}
