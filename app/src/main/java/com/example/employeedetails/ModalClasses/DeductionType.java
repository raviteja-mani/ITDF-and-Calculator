package com.example.employeedetails.ModalClasses;

public class DeductionType {
    String text;
    int declared;
    int eligible;

    public DeductionType(String text, int declared, int elegible) {
        this.text = text;
        this.declared = declared;
        this.eligible = elegible;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDeclared() {
        return declared;
    }

    public void setDeclared(int declared) {
        this.declared = declared;
    }

    public int getEligible() {
        return eligible;
    }

    public void setEligible(int eligible) {
        this.eligible = eligible;
    }


}
