package com.example.employeedetails;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version=3,entities = {HRAItem.class},exportSchema = false)
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
                        MainDatabase.class, "ALL_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                HRADao dao = INSTANCE.hradao();
                dao.deleteAll();
                HRAItem jan=new HRAItem(10,"JANUARY","","","",0);
                HRAItem feb=new HRAItem(11,"FEBRUARY","","","",0);
                HRAItem mar=new HRAItem(12,"MARCH","","","",0);
                HRAItem ap=new HRAItem(1,"APRIL","","","",0);
                HRAItem may=new HRAItem(2,"MAY","","","",0);
                HRAItem jun=new HRAItem(3,"JUNE","","","",0);
                HRAItem jul=new HRAItem(4,"JULY","","","",0);
                HRAItem aug=new HRAItem(5,"AUGUST","","","",0);
                HRAItem sep=new HRAItem(6,"SEPTEMBER","","","",0);
                HRAItem oct=new HRAItem(7,"OCTOBER","","","",0);
                HRAItem nov=new HRAItem(8,"NOVEMBER","","","",0);
                HRAItem dec=new HRAItem(9,"DECEMBER","","","",0);
                dao.insert(jan);
                dao.insert(feb);
                dao.insert(mar);
                dao.insert(ap);
                dao.insert(may);
                dao.insert(jun);
                dao.insert(jul);
                dao.insert(aug);
                dao.insert(sep);
                dao.insert(oct);
                dao.insert(nov);
                dao.insert(dec);
            });
        }
    };

}
