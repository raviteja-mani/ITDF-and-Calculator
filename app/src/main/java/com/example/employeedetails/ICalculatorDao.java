package com.example.employeedetails;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.ModalClasses.HouseProperty;
import com.example.employeedetails.ModalClasses.PESclass;

@Dao
public interface ICalculatorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PESclass item);

    @Query("select * from PES_table")
    LiveData<PESclass> getPESall();

//    @Delete
//    void delete(PESclass item);

    @Update
    int updatePES(PESclass item);

    //House Property income

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHP(HouseProperty item);

    @Query("select * from HP_table")
    LiveData<HouseProperty> getHPIncome();

    @Update
    int updateHPIncome(HouseProperty item);

}
