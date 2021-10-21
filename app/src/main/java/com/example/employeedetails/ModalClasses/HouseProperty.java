package com.example.employeedetails.ModalClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "HP_table")
public class HouseProperty implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public long income_from_SOP;

    public long annual_letable_value;

    public long municipal_tax;

    public long interet_on_houseLoan;

    public HouseProperty(long income_from_SOP, long annual_letable_value, long municipal_tax, long interet_on_houseLoan) {
        this.income_from_SOP = income_from_SOP;
        this.annual_letable_value = annual_letable_value;
        this.municipal_tax = municipal_tax;
        this.interet_on_houseLoan = interet_on_houseLoan;
    }

    public void setIncome_from_SOP(long income_from_SOP) {
        this.income_from_SOP = income_from_SOP;
    }

    public void setAnnual_letable_value(long annual_letable_value) {
        this.annual_letable_value = annual_letable_value;
    }

    public void setMunicipal_tax(long municipal_tax) {
        this.municipal_tax = municipal_tax;
    }

    public void setInteret_on_houseLoan(long interet_on_houseLoan) {
        this.interet_on_houseLoan = interet_on_houseLoan;
    }

    public long getIncome_from_SOP() {
        return income_from_SOP;
    }

    public long getAnnual_letable_value() {
        return annual_letable_value;
    }

    public long getMunicipal_tax() {
        return municipal_tax;
    }


    public long getInteret_on_houseLoan() {
        return interet_on_houseLoan;
    }
    public float getNetAnnualValue(){
        return (float)(getAnnual_letable_value())-(float)(getMunicipal_tax());
    }
    public float getStandartDeduction(){
        return (float) (0.3*getNetAnnualValue());
    }
    public float getIncomefromLetout(){
        return (getNetAnnualValue()-(float)(getInteret_on_houseLoan())-getStandartDeduction());
    }
    public float getTotalIncomefromHP(){
        return -(float)(getIncome_from_SOP())+getIncomefromLetout();
    }
}
