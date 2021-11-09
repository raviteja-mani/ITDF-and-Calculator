package com.example.employeedetails.ModalClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="HRA_table")
public class HRAItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int ip;

    public int id;
    public String month;
    public String Landlordname;
    public String Pancard;
    public String location;

    public int amount;

    public HRAItem() {
    }

    public HRAItem(int id, String month, String landlordname, String pancard, String location, int amount) {
        this.id = id;
        this.month = month;
        Landlordname = landlordname;
        Pancard = pancard;
        this.location = location;
        this.amount = amount;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setLandlordname(String landlordname) {
        Landlordname = landlordname;
    }

    public void setPancard(String pancard) {
        Pancard = pancard;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setId(int id){
        this.id=id;
    }




    public int getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public String getLandlordname() {
        return Landlordname;
    }

    public String getPancard() {
        return Pancard;
    }

    public String getLocation() {
        return location;
    }

    public int getAmount() {
        return amount;
    }
}
