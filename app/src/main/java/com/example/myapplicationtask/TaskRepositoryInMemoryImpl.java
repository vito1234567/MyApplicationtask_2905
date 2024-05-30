package com.example.myapplicationtask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thorsten on 23.03.20.
 */

public class TaskRepositoryInMemoryImpl implements TaskRepository {

    private static TaskRepositoryInMemoryImpl instance;

    private List<Task> mTasks;


    public static synchronized TaskRepositoryInMemoryImpl getInstance() {
        if (instance == null) {
            instance = new TaskRepositoryInMemoryImpl();
        }
        return instance;
    }


    private TaskRepositoryInMemoryImpl() {
        mTasks = new ArrayList<>();

        Task myTask = new Task("Empty the trash");
        myTask.setDescription("Someone has to get the dirty jobs done...");
        myTask.setDone(true);
        mTasks.add(myTask);
        mTasks.add(new Task("Groceries"));
        mTasks.add(new Task("Call parents"));
        myTask = new Task("Do Android programming");
        myTask.setDescription("Nobody said it would be easy!");
        myTask.setDone(false);
        mTasks.add(myTask);

//        for (int i=0; i<40; i++)
//            mTasks.add(new Task("Task" + i));
    }

    @Override
    public List<Task> loadTasks() {
        return mTasks;
    }

    @Override
    public void deleteFinishedTasks() {
        for (int i=0; i<mTasks.size(); i++) {
            Task task = mTasks.get(i);
            if (task.isDone()) {
                mTasks.remove(task);
                i--;
            }
        }
    }

    public void addTask(Task task) {
        // Add a new task to the repository
        mTasks.add(task);
    }

    public void updateTask(Task task) {
        // Update an existing task in the repository
        for (Task t : mTasks) {
            if (t.getId() == task.getId()) {
                t.setShortName(task.getShortName());
                t.setDescription(task.getDescription());
                t.setDone(task.isDone());
                // Update other fields as needed
                break;
            }
        }
    }
}
