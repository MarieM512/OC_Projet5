package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.SaveTodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ProjectDaoTest {

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private SaveTodocDatabase mDatabase;
    private final Project[] mProjects = Project.getAllProjects();

    @Before
    public void initDb() {
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                        SaveTodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void insertProject() throws InterruptedException {
        List<Project> projectList = LiveDataTestUtil.getValue(this.mDatabase.mProjectDao().getProject());
        Assert.assertTrue(projectList.isEmpty());
        this.mDatabase.mProjectDao().insertProjects(mProjects);
        projectList = LiveDataTestUtil.getValue(this.mDatabase.mProjectDao().getProject());
        Assert.assertEquals(3, projectList.size());
    }
}
