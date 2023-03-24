package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    // Fetch a project
    @Query("SELECT * FROM project")
    LiveData<List<Project>> getProject();

    // Add all the projects
    @Insert
    void insertProjects(Project... projects);
}
