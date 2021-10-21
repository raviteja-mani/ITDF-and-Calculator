package com.example.employeedetails.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.RepositoryHP;
import com.example.employeedetails.RepositoryPES;

public class HPviewModel extends AndroidViewModel {
    private RepositoryHP repositoryHP;
    private final LiveData<HouseProperty> HPitem;

    public HPviewModel(@NonNull Application application) {
        super(application);
        repositoryHP=new RepositoryHP(application);
        HPitem=repositoryHP.getHPincome();
    }
    public LiveData<HouseProperty> getPES(){
        return HPitem;
    }
    public void insert(HouseProperty item){
        repositoryHP.insert(item);
    }
    public void update(HouseProperty item){
        repositoryHP.Update(item);
    }
}
