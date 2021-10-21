package com.example.employeedetails;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.ModalClasses.HRAItem;

import java.util.List;

@Dao
public interface HRADao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HRAItem item);

    @Query("SELECT * FROM HRA_table ORDER BY id ASC")
    LiveData<List<HRAItem>> getAll();

    @Query("DELETE FROM HRA_table where 1=1")
    void deleteAll();

    @Delete
    void delete(HRAItem item);

    @Update
    int update(HRAItem item);


}
