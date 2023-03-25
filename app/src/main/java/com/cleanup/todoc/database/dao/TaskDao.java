package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // Fetch all the tasks
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    // Add a task
    @Insert
    long addTask(Task task);

    // Delete a task
    @Query("DELETE FROM Task WHERE id = :taskId")
    int deleteTask(long taskId);
}
