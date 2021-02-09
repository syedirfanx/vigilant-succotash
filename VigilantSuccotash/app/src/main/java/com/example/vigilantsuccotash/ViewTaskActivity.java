package com.example.vigilantsuccotash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewTaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<TaskModelClass> taskModelClasses = databaseHelperClass.getTaskList();

        if (taskModelClasses.size() > 0){
            TaskAdapterClass taskadapterclass = new TaskAdapterClass(taskModelClasses,ViewTaskActivity.this);
            recyclerView.setAdapter(taskadapterclass);
        }else {
            Toast.makeText(this, "There is no task in the database", Toast.LENGTH_SHORT).show();
        }




    }
}
