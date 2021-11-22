package com.example.employeedetails.ModalClasses;

import java.io.Serializable;

public class Exemptions implements Serializable {
    private long baseSalary;
    private long dearnessAllowance;
    private long HRARecieved;
    private long totalRentPaid;
    private String cityType;

    public Exemptions(long baseSalary, long dearnessAllowance, long HRARecieved, long totalRentPaid, String cityType) {
        this.baseSalary = baseSalary;
        this.dearnessAllowance = dearnessAllowance;
        this.HRARecieved = HRARecieved;
        this.totalRentPaid = totalRentPaid;
        this.cityType = cityType;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setDearnessAllowance(int dearnessAllowance) {
        this.dearnessAllowance = dearnessAllowance;
    }

    public void setHRARecieved(int HRARecieved) {
        this.HRARecieved = HRARecieved;
    }

    public void setTotalRentPaid(int totalRentPaid) {
        this.totalRentPaid = totalRentPaid;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    public long getBaseSalary() {
        return baseSalary;
    }

    public long getDearnessAllowance() {
        return dearnessAllowance;
    }

    public long getHRARecieved() {
        return HRARecieved;
    }

    public long getTotalRentPaid() {
        return totalRentPaid;
    }

    public String getCityType() {
        return cityType;
    }
    public long getTotalExemptions(){
    long excess=0;
    long fy=0;
    long hra=getHRARecieved();
        if(cityType.equals("Yes")){
        fy= (long) (getBaseSalary()*0.50);
        }
        else fy=(long)(getBaseSalary()*0.40);
        excess=(getTotalRentPaid()-(long)(getBaseSalary()*0.10))<=0?0:(getTotalRentPaid()-(long)(getBaseSalary()*0.10));
        if(hra<fy&&hra<excess) return hra;
        else if(fy<hra&&fy<excess) return fy;
        else return excess;

    }
}
