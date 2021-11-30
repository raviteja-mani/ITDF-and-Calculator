package com.example.employeedetails;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.employeedetails.ModalClasses.PESclass;
//not used
public class RepositoryPES {
private ICalculatorDao dao;
private LiveData<PESclass> itemPES;

    public RepositoryPES(Application application) {
        MainDatabase database=MainDatabase.getdatabase(application);
        dao= database.iCalculatorDao();
        this.itemPES=dao.getPESall();
    }
    public LiveData<PESclass> getPES(){
     return itemPES;
    }
    public void insert(PESclass item){
        MainDatabase.databaseWriteExecutor.execute(() -> {
            dao.insert(item);
        });
    }
    public void Update(PESclass item){
        MainDatabase.databaseWriteExecutor.execute(() -> {
            dao.updatePES(item);
        });
    }
}
