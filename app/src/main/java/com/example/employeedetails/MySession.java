package com.example.employeedetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.ModalClasses.HRAItem;
import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.OtherDeductions;
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
    Generalfunctions funcs;
    public MySession(Context context) {
        sharedprefs=context.getSharedPreferences("employeeDetails", AppCompatActivity.MODE_PRIVATE);
        funcs=new Generalfunctions(context);
        exemptions=funcs.getExemptions();

    }
//    public MySession(List<HRAItem> listOfHRA, Otherimcome otherIncome, PESclass pes, HouseProperty houseProperty) {
//        this.listOfHRA = listOfHRA;
//        this.otherIncome = otherIncome;
//        this.pes = pes;
//        this.houseProperty = houseProperty;
//    }

    public void setListOfHRA(List<HRAItem> listOfHRA) {
        this.listOfHRA = listOfHRA;
    }

    public void setOtherIncome(Otherimcome otherIncome) {
        funcs.setOtherIncome(otherIncome);
    }

    public void setPes(PESclass pes) {
//        this.pes = pes;
    funcs.setPES(pes);
    }

    public void setHouseProperty(HouseProperty houseProperty) {
        funcs.setHouseProperty(houseProperty);
    }

    public List<HRAItem> getListOfHRA() {
        return listOfHRA;
    }

    public Otherimcome getOtherIncome() {

        return funcs.getOtherIncome();
    }

    public PESclass getPes() {
        return funcs.getPES();
    }

    public HouseProperty getHouseProperty() {
        return funcs.getHouseProperty();
    }
    public int getTotalOtherIncome(){
        Otherimcome o=getOtherIncome();
        return o.getOtherIncome()+o.getInterestOnSavings();
    }
    public int getTotalDeductions(){
        deductions=funcs.getDeductionArray();
        int total=0;
        for(DeductionType d:deductions){
            total+=d.getDeclared();
        }
        if(total>150000)
        return 150000;
        else return total;
    }
    public long getTotalExemptions(){
        return exemptions.getTotalExemptions();
    }
    public Exemptions getExemptions(){
        return funcs.getExemptions();
    }
    public void setExemptions(Exemptions e){
        funcs.setExemptions(e);
    }
    public ArrayList<DeductionType> getDeductions(){
        return funcs.getDeductionArray();
    }
    public void setDeductions(ArrayList<DeductionType> arr){
        funcs.setDeductionArray(arr);
    }
    public void setGrossSalary(long val){
        funcs.setGrossSalary(val);
    }
    public long getGrossSalary(){
        return funcs.getGrossSalary();
    }
    public void setOtherDeductions(ArrayList<OtherDeductions> deductions1){
        funcs.setOtherDeductions(deductions1);
    }
    public ArrayList<OtherDeductions> getOtherDeductions(){
        return funcs.getOtherDeductions();
    }

    public long getTotalOtherDeductions() {
        ArrayList<OtherDeductions> deductions1=getOtherDeductions();
        long total=0;
        for(OtherDeductions od:deductions1){
            total+=od.getElegible();
        }
        return total;
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        return isConnected;
    }
}
