package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.SaveTodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4ClassRunner.class)
public class TaskDaoTest {

    private static final long PROJECT_ID = 1L;
    private static final long TASK_ID = 1;
    private static final Task TASK_DEMO = new Task(TASK_ID, PROJECT_ID, "Test_name", 78);

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
        this.mDatabase.mProjectDao().insertProjects(mProjects);
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void getNoTask() throws InterruptedException {
        List<Task> task = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks());
        Assert.assertEquals(0, task.size());
    }

    @Test
    public void insertTask() throws InterruptedException {
        this.mDatabase.mTaskDao().addTask(TASK_DEMO);
        List<Task> listTask = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks());
        Assert.assertEquals(1, listTask.size());
        Task task = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTask(TASK_DEMO.getId()));
        Assert.assertTrue(task.getName().equals(TASK_DEMO.getName()) && task.getId() == TASK_ID);
    }

    @Test
    public void deleteTask() throws InterruptedException {
        this.mDatabase.mTaskDao().addTask(TASK_DEMO);
        List<Task> listTask = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks());
        Assert.assertEquals(1, listTask.size());
        Task task = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTask(TASK_ID));
        Assert.assertTrue(task.getName().equals(TASK_DEMO.getName()) && task.getId() == TASK_ID);
        this.mDatabase.mTaskDao().deleteTask(TASK_DEMO.getId());
        listTask = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks());
        Assert.assertEquals(0, listTask.size());
    }
}
