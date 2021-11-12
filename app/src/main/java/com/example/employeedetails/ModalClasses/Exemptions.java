package com.example.employeedetails.ModalClasses;

public class Exemptions {
    private int baseSalary;
    private int dearnessAllowance;
    private int HRARecieved;
    private int totalRentPaid;
    private String cityType;

    public Exemptions(int baseSalary, int dearnessAllowance, int HRARecieved, int totalRentPaid, String cityType) {
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

    public int getBaseSalary() {
        return baseSalary;
    }

    public int getDearnessAllowance() {
        return dearnessAllowance;
    }

    public int getHRARecieved() {
        return HRARecieved;
    }

    public int getTotalRentPaid() {
        return totalRentPaid;
    }

    public String getCityType() {
        return cityType;
    }
    public int getTotalExemptions(){

        if(cityType.equals("Yes")){
            
        }
        return 0;
    }
}
