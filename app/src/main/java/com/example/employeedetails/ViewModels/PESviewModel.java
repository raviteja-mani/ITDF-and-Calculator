package com.example.employeedetails.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.RepositoryPES;
//Not used
public class PESviewModel extends AndroidViewModel {
    private RepositoryPES repositoryPES;
    private  LiveData<PESclass> PESitem;

    public PESviewModel(@NonNull Application application) {
        super(application);
        repositoryPES=new RepositoryPES(application);
        PESitem=repositoryPES.getPES();
    }
    public LiveData<PESclass> getPES(){
        return PESitem;
    }
    public void insert(PESclass item){
        repositoryPES.insert(item);
    }
    public void update(PESclass item){
        repositoryPES.Update(item);
    }
}
