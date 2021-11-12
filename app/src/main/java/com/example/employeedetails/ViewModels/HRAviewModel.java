package com.example.employeedetails.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeedetails.HRARepository;
import com.example.employeedetails.ModalClasses.HRAItem;

import java.util.List;

public class HRAviewModel extends AndroidViewModel {
    private HRARepository repository;
    private final LiveData<List<HRAItem>> HRAall;
    public HRAviewModel(@NonNull Application application) {
        super(application);
        repository=new HRARepository(application);
        HRAall=repository.getHRAItemAll();
//        Context context=application.getApplicationContext();
    }
    public LiveData<List<HRAItem>> getHRAItemAll(){
        return HRAall;
    }
    public void insert(HRAItem item){
        repository.insert(item);
    }
    public void delete(HRAItem item){
        repository.delete(item);
    }
    public void update(HRAItem item){
        repository.update(item);
    }
//    public void deleteAll(){
//        repository.deleteAll();
//    }


}
