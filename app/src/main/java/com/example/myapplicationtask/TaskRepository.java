package com.example.myapplicationtask;

import java.util.List;

/**
 * Created by thorsten on 23.03.20.
 */

public interface TaskRepository {

    List<Task> loadTasks();

    void deleteFinishedTasks();

   void addTask(Task task);

   void updateTask(Task task);
}
