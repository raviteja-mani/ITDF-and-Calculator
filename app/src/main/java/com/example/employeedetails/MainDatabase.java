package com.example.employeedetails;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version=1,entities = {HRAItem.class},exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    public abstract HRADao hradao();


    private static volatile MainDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static MainDatabase getdatabase(final Context context){
        synchronized (MainDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MainDatabase.class, "word_database")
                        .build();
            }
        }
        return INSTANCE;
    }


}
