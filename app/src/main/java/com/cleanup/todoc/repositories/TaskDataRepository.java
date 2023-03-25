package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.mTaskDao = taskDao;
    }

    // Fetch all the tasks
    public LiveData<List<Task>> getTasks() {
        return this.mTaskDao.getTasks();
    }

    // Add a task
    public void addTask(Task task) {
        mTaskDao.addTask(task);
    }

    // Delete a task
    public void deleteTask(long taskId) {
        mTaskDao.deleteTask(taskId);
    }
}
