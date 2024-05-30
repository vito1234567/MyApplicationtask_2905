package com.example.myapplicationtask;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationtask.databinding.ActivityCreateListTaskBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CreateListTask extends AppCompatActivity {
    private TaskList taskList;
    private ActivityCreateListTaskBinding binding;

    private static int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inizializzazione del binding
        binding = ActivityCreateListTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState != null) {
            String category = savedInstanceState.getString("editCategory");
            binding.editCategory.setText(category);
        }

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskList(v);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        // Salva lo stato
        saveInstanceState.putString("editCategory", Objects.requireNonNull(binding.editCategory.getText()).toString());
        super.onSaveInstanceState(saveInstanceState);
    }

    public void addTaskList(View v){
        EditText mTaskList = binding.editCategory;
        String fieldName = Objects.requireNonNull(mTaskList.getText()).toString();
        taskList = new TaskList(++id, fieldName);
        mTaskList.setText("");

        Snackbar.make(v, "New Task list Added", Snackbar.LENGTH_LONG).show();
    }
}