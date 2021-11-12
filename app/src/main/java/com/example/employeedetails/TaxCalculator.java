package com.example.employeedetails;

public interface TaxCalculator {
    long calculateTaxableIncome();
    long calculateIncomeTaxPayable();
    long calculateHealth();
    long calculateSurcharge();
}
