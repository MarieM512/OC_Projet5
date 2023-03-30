package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository mTaskDataRepository;
    private final Executor mExecutor;

    // DATA
    private LiveData<List<Task>> mLiveData;

    public TaskViewModel(TaskDataRepository taskDataRepository, Executor executor) {
        this.mTaskDataRepository = taskDataRepository;
        this.mExecutor = executor;
    }

    public void init() {
        if (this.mLiveData == null) {
            mLiveData = mTaskDataRepository.getTasks();
        }
    }

    public LiveData<List<Task>> getTasks() {
        return mTaskDataRepository.getTasks();
    }

    public void addTask(Task task) {
        mExecutor.execute(() ->
                mTaskDataRepository.addTask(task)
        );
    }

    public void deleteTask(Task task) {
        mExecutor.execute(() ->
                mTaskDataRepository.deleteTask(task)
        );
    }
}
