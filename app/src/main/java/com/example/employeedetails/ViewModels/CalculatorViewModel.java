package com.example.employeedetails.ViewModels;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Exemptions;
import com.example.employeedetails.ModalClasses.OtherDeductins;
import com.example.employeedetails.ModalClasses.Otherimcome;
import com.example.employeedetails.ModalClasses.PESclass;

public class CalculatorViewModel extends ViewModel {
    private static long grossSalary;
    private static Exemptions e;
    private static long ProfessionalTax;
    private static long standartDeductions=50000;
    private static PESclass pes;
    private static Otherimcome otherincome;
    private static DeductionType deductions;
    private static OtherDeductins otherDeductins;
    private MutableLiveData<Integer> taxableIncome=new MutableLiveData<>();
    private MutableLiveData<Integer> incometaxpayable=new MutableLiveData<>();
    private MutableLiveData<Integer> surcharge=new MutableLiveData<>();
    private MutableLiveData<Integer> health=new MutableLiveData<>();
    public static long getGrossSalary() {
        return grossSalary;
    }

    public static void setGrossSalary(long grossSalary) {
        CalculatorViewModel.grossSalary = grossSalary;
    }

    public static Exemptions getE() {
        return e;
    }

    public static void setE(Exemptions e) {
        CalculatorViewModel.e = e;
    }

    public static long getProfessionalTax() {
        return ProfessionalTax;
    }

    public static void setProfessionalTax(long professionalTax) {
        ProfessionalTax = professionalTax;
    }

    public static long getStandartDeductions() {
        return standartDeductions;
    }

    public static void setStandartDeductions(long standartDeductions) {
        CalculatorViewModel.standartDeductions = standartDeductions;
    }

    public static PESclass getPes() {
        return pes;
    }

    public static void setPes(PESclass pes) {
        CalculatorViewModel.pes = pes;
    }

    public static Otherimcome getOtherincome() {
        return otherincome;
    }

    public static void setOtherincome(Otherimcome otherincome) {
        CalculatorViewModel.otherincome = otherincome;
    }

    public static DeductionType getDeductions() {
        return deductions;
    }

    public static void setDeductions(DeductionType deductions) {
        CalculatorViewModel.deductions = deductions;
    }

    public static OtherDeductins getOtherDeductins() {
        return otherDeductins;
    }

    public static void setOtherDeductins(OtherDeductins otherDeductins) {
        CalculatorViewModel.otherDeductins = otherDeductins;
    }




}
