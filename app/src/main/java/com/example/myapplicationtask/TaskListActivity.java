package com.example.myapplicationtask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationtask.databinding.ActivityListTaskBinding;

import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;


public class TaskListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private ActivityListTaskBinding binding; //used for the connect with xml object
    private List<Task> tasks;

    private static final String BUNDLE_TASKS_KEY = "task";

    private TaskListAdapter adapter;


    @Override //method used when the activity starts
    protected void onCreate(Bundle savedInstanceState) {
        //invokes the superclass's method
        super.onCreate(savedInstanceState);

        binding = ActivityListTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            //if it's null it's created by first time so we can load the task in repository
            tasks = TaskRepositoryInMemoryImpl.getInstance().loadTasks();
        } else {
            // we obtain the task by the status saved because it's not created now
            tasks = savedInstanceState.getParcelableArrayList(BUNDLE_TASKS_KEY);
        }

        adapter = new TaskListAdapter(tasks);
        RecyclerView listView = binding.listview;


        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TaskListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(BUNDLE_TASKS_KEY, new ArrayList<>(tasks));
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.nav_create_task){
            Intent intent = new Intent(TaskListActivity.this, CreateListTask.class);
            startActivity(intent);
        }
        return true;
    }

}
