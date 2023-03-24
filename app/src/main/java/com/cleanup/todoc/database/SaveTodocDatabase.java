package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class SaveTodocDatabase extends RoomDatabase {

    // Singleton
    private static volatile SaveTodocDatabase INSTANCE;

    // DAO
    public abstract TaskDao mTaskDao();
    public abstract ProjectDao mProjectDao();

    // Instance
    public static SaveTodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveTodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveTodocDatabase.class, "MyDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
