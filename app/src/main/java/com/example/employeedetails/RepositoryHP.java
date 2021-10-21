package com.example.employeedetails;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.employeedetails.ModalClasses.HouseProperty;


public class RepositoryHP {
    private ICalculatorDao dao;
    private LiveData<HouseProperty> itemHP;
    public RepositoryHP(Application application) {
        MainDatabase database=MainDatabase.getdatabase(application);
        dao= database.iCalculatorDao();
        this.itemHP=dao.getHPIncome();
    }
    public LiveData<HouseProperty> getHPincome(){
        return itemHP;
    }
    public void insert(HouseProperty item){
        MainDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertHP(item);
        });
    }
    public void Update(HouseProperty item){
        MainDatabase.databaseWriteExecutor.execute(() -> {
            dao.updateHPIncome(item);
        });
    }
}
