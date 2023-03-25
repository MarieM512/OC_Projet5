package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.mProjectDao = projectDao;
    }

    // Fetch project
    public LiveData<List<Project>> getProject() {
        return this.mProjectDao.getProject();
    }
}
