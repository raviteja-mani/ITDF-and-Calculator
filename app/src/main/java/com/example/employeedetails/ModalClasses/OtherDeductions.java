package com.example.employeedetails.ModalClasses;

import java.io.Serializable;

public class OtherDeductions implements Serializable {
    private String title;
    private long declared;
    private long elegible;
    private long constriant;

    public OtherDeductions(String title, long declared, long elegible, long constriant) {
        this.title = title;
        this.declared = declared;
        this.elegible = elegible;
        this.constriant = constriant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDeclared() {
        return declared;
    }

    public void setDeclared(long declared) {
        this.declared = declared;
    }

    public long getElegible() {
        return elegible;
    }

    public void setElegible(long elegible) {
        this.elegible = elegible;
    }

    public long getConstriant() {
        return constriant;
    }

    public void setConstriant(long constriant) {
        this.constriant = constriant;
    }
}
