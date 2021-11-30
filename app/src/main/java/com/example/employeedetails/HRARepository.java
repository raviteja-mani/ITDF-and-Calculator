package com.example.employeedetails;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.employeedetails.ModalClasses.HRAItem;

import java.util.List;

public class HRARepository {
    private HRADao hraDao;
    private LiveData<List<HRAItem>> allHRAitems;

    public HRARepository(Application application) {
        MainDatabase database=MainDatabase.getdatabase(application);
        this.hraDao= database.hradao();
        this.allHRAitems= hraDao.getAll();
    }
    public LiveData<List<HRAItem>> getHRAItemAll() {
        return allHRAitems;
    }
    public void insert(HRAItem item){
        MainDatabase.databaseWriteExecutor.execute(() -> {
            hraDao.insert(item);
        });
    }
    public void delete(HRAItem item){
        MainDatabase.databaseWriteExecutor.execute(() -> {
            hraDao.delete(item);
        });
    }
    public void update(HRAItem item){
        MainDatabase.databaseWriteExecutor.execute(()->{
            hraDao.update(item);
        });
    }

}
