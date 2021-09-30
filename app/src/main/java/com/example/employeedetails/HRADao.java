package com.example.employeedetails;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HRADao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HRAItem item);

    @Query("SELECT * FROM HRA_table ORDER BY ID ASC")
    LiveData<List<HRAItem>> getAll();

    @Delete
    void delete(HRAItem item);

}
