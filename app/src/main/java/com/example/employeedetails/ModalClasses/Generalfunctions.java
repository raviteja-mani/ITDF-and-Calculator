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
    public static String[] deductionTitles={
            "1) Employee Provident Fund",
            "2) Voluntary Provident Fund",
            "3) Provident Fund contributed with previous employer",
            "4) Deduction under life insurance pension scheme (80CCC)",
            "5) Public Provident Fund",
            "6) Children Education",
            "7) National Savings Certificate",
            "8) Accrued NSC Interest",
            "9) Life Insurance premium paid",
            "10) Housing Loan principal repayment",
            "11) Sukanya Samriddi Account",
            "12) Approved super annuation"};
    public static String[] otherdeductionTitles={
            "1) Medical Insurance Premium (Sec 80D)",
            "2) Medical Insurance Premium for parents (sec 80D)",
            "3) Medical Insurance Premium paid for senior Citizen (Parents)",
            "4) Medical for Handicapped Dependents (Sec 80DD)",
            "5) Medical for Handicapped Dependents (severe disability) (Sec 80DD)",
            "6) Medical for Specified Diseases (Sec 80DDB)",
            "7) Medical for Specified Diseases for Senior Citizen (Sec 80DDB)",
            "8) Interest Paid on Higher Education Loan (Sec 80E)",
            "9) Donation for Approved Fund and Charities (Sec 80G) Central Govt",
            "10) Deduction for Permanent Disability (Sec 80U) Severe",
            "11) Interest on House Property - Additional Exemption"
    };
//    public static long constraints[]={25000,25000,0,40000};
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
    public HouseProperty getHouseProperty(){
        Gson gson=new Gson();
        String s=sharedPreferences.getString("houseProperty",gson.toJson(new HouseProperty(0,
                0,0,0),HouseProperty.class));
        return gson.fromJson(s,HouseProperty.class);
    }
    public void setHouseProperty(HouseProperty hp){
        Gson gson=new Gson();
        prefsEditor.putString("houseProperty",gson.toJson(hp));
        prefsEditor.commit();
    }
    public void setPES(PESclass pes){
        Gson gson=new Gson();
        prefsEditor.putString("pesItem",gson.toJson(pes));
        prefsEditor.commit();
    }
    public PESclass getPES(){
        Gson gson=new Gson();
        String s=sharedPreferences.getString("pesItem",gson.toJson(new PESclass(0,
                0,0,0,0),PESclass.class));
        return gson.fromJson(s,PESclass.class);
    }
    public void setOtherIncome(Otherimcome os){
        Gson gson=new Gson();
        prefsEditor.putString("OtherIncomeItem",gson.toJson(os));
        prefsEditor.commit();
    }
    public Otherimcome getOtherIncome(){
        Gson gson=new Gson();
        String s=sharedPreferences.getString("OtherIncomeItem",gson.toJson(new Otherimcome(0,0 ),Otherimcome.class));
        return gson.fromJson(s,Otherimcome.class);
    }
    public void setGrossSalary(long val){
        Gson gson=new Gson();
        prefsEditor.putLong("GrossSalary",val).commit();
    }
    public long getGrossSalary(){
        return sharedPreferences.getLong("GrossSalary",0);
    }
    public void setOtherDeductions(ArrayList<OtherDeductions> deductions){
        Gson gson=new Gson();
        prefsEditor.putString("OtherDeductions",gson.toJson(deductions)).commit();
    }
    public ArrayList<OtherDeductions> getOtherDeductions(){
        Gson gson=new Gson();
        OtherDeductions[] arr=gson.fromJson(sharedPreferences.getString("OtherDeductions",gson.toJson(getDefaultOtherDeductions())),OtherDeductions[].class);
        ArrayList<OtherDeductions> a=new ArrayList<OtherDeductions>(Arrays.asList(arr));
        return a;
    }
    public ArrayList<OtherDeductions> getDefaultOtherDeductions(){
        ArrayList<OtherDeductions> arr=new ArrayList<>();
        for(String title:otherdeductionTitles){
            arr.add(new OtherDeductions(title,0,0,0));
        }
        return arr;
    }

    public ArrayList<DeductionType> getITDFDeductionArray() {
        Gson gson=new Gson();
        DeductionType[] arr=gson.fromJson(sharedPreferences.getString("ITDFdeductions",gson.toJson(defaultDeductions())),
                DeductionType[].class);
        ArrayList<DeductionType> a=new ArrayList<DeductionType>(Arrays.asList(arr));
        return a;
    }

    public ArrayList<OtherDeductions> getITDFOtherDeductions() {
        Gson gson=new Gson();
        OtherDeductions[] arr=gson.fromJson(sharedPreferences.getString("ITFDOtherDeductions",gson.toJson(getDefaultOtherDeductions())),OtherDeductions[].class);
        ArrayList<OtherDeductions> a=new ArrayList<OtherDeductions>(Arrays.asList(arr));
        return a;
    }

    public void setITDFDeductionArray(ArrayList<DeductionType> deductions) {
        Gson gson=new Gson();
        prefsEditor.putString("ITDFdeductions",gson.toJson(deductions));
        prefsEditor.commit();
    }

    public void setITDFOtherDeductionArray(ArrayList<OtherDeductions> deductions) {
        Gson gson=new Gson();
        prefsEditor.putString("ITDFOtherdeductions",gson.toJson(deductions));
        prefsEditor.commit();
    }
    public Otherimcome getOtherIncome(String type){
        Gson gson=new Gson();
        return gson.fromJson(sharedPreferences.getString("OS"+type,gson.toJson(new Otherimcome(0,0 ))), Otherimcome.class);
    }

    public PESclass getPES(String type) {
        Gson gson=new Gson();
        return gson.fromJson(sharedPreferences.getString("pesItem"+type,gson.toJson(new PESclass(0,0 ,0,0,0))), PESclass.class);
    }
    public void setPES(PESclass pes,String type){
        Gson gson=new Gson();
        prefsEditor.putString("pesItem"+type,gson.toJson(pes));
        prefsEditor.commit();
    }
}
