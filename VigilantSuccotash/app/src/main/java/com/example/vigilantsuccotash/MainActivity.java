package com.example.vigilantsuccotash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_started_at, editText_end_at, editText_dated, editText_is_whole_day, editText_is_completed, editText_completed_at, editText_description;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_started_at = findViewById(R.id.edittext_start_at);
        editText_end_at = findViewById(R.id.edittext_end_at);
        editText_dated = findViewById(R.id.edittext_dated);
        editText_is_whole_day = findViewById(R.id.edittext_is_whole_day);
        editText_is_completed = findViewById(R.id.edittext_is_completed);
        editText_completed_at = findViewById(R.id.edittext_completed_at);
        editText_description = findViewById(R.id.edittext_description);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float stringStartedAt = Float.parseFloat(editText_started_at.getText().toString());
                float stringEndAt = Float.parseFloat(editText_end_at.getText().toString());
                float stringDated = Float.parseFloat(editText_dated.getText().toString());
                int stringIsWholeDay = Integer.parseInt(editText_is_whole_day.getText().toString());
                int stringIsCompleted = Integer.parseInt(editText_is_completed.getText().toString());
                float stringCompletedAt = Float.parseFloat(editText_completed_at.getText().toString());
                String stringDescription = editText_description.getText().toString();

                if (stringDescription.length() <=0){
                    Toast.makeText(MainActivity.this, "Enter Description", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(MainActivity.this);
                    TaskModelClass taskModelClass = new TaskModelClass(stringStartedAt, stringEndAt, stringDated, stringIsWholeDay, stringIsCompleted, stringCompletedAt, stringDescription);
                    databaseHelperClass.addTask(taskModelClass);
                    Toast.makeText(MainActivity.this, "Add Task Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewTaskActivity.class);
                startActivity(intent);
            }
        });


    }
}