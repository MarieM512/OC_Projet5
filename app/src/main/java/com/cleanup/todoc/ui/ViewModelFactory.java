package com.cleanup.todoc.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.SaveTodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository mTaskDataRepository;
    private final ProjectDataRepository mProjectDataRepository;
    private final Executor mExecutor;
    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context) {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    private ViewModelFactory(Context context) {
        SaveTodocDatabase database = SaveTodocDatabase.getInstance(context);
        this.mTaskDataRepository = new TaskDataRepository(database.mTaskDao());
        this.mProjectDataRepository = new ProjectDataRepository(database.mProjectDao());
        this.mExecutor = Executors.newSingleThreadExecutor();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(mTaskDataRepository, mProjectDataRepository, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
