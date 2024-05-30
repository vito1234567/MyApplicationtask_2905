package com.example.myapplicationtask;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private int id;
    private String name;
    private List<Task> tasks;

    public TaskList(int id,String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
