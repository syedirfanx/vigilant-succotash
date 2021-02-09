package com.example.vigilantsuccotash;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapterClass extends RecyclerView.Adapter<TaskAdapterClass.ViewHolder> {

    List<TaskModelClass> task;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public TaskAdapterClass(List<TaskModelClass> task, Context context) {
        this.task = task;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.task_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TaskModelClass taskModelClass = task.get(position);

        holder.textViewID.setText(Integer.toString(taskModelClass.getTaskID()));
        holder.editText_start_at.setText((int) taskModelClass.getStartAt());
        holder.editText_end_at.setText((int) taskModelClass.getEndAt());
        holder.editText_dated.setText((int) taskModelClass.getDated());
        holder.editText_is_whole_day.setText(taskModelClass.getIsWholeDay());
        holder.editText_is_completed.setText(taskModelClass.getIsCompleted());
        holder.editText_completed_at.setText((int) taskModelClass.getCompletedAt());
        holder.editText_description.setText(taskModelClass.getDescription());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float stringStartAt = Float.parseFloat(holder.editText_start_at.getText().toString());
                float stringEndAt = Float.parseFloat(holder.editText_end_at.getText().toString());
                float stringDated = Float.parseFloat(holder.editText_dated.getText().toString());
                Integer stringIsWholeDay = Integer.valueOf(holder.editText_is_whole_day.getText().toString());
                Integer stringIsCompleted = Integer.valueOf(holder.editText_is_completed.getText().toString());
                float stringCompletedAt = Float.parseFloat(holder.editText_completed_at.getText().toString());
                String stringDescription = holder.editText_description.getText().toString();

                databaseHelperClass.updateTask(new TaskModelClass(taskModelClass.getTaskID(),stringStartAt, stringEndAt, stringDated, stringIsWholeDay, stringIsCompleted, stringCompletedAt, stringDescription));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteTask(taskModelClass.getTaskID());
                task.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_start_at;
        EditText editText_end_at;
        EditText editText_dated;
        EditText editText_is_whole_day;
        EditText editText_is_completed;
        EditText editText_completed_at;
        EditText editText_description;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.task_id);
            editText_start_at = itemView.findViewById(R.id.edittext_start_at);
            editText_end_at = itemView.findViewById(R.id.edittext_end_at);
            editText_dated = itemView.findViewById(R.id.edittext_dated);
            editText_is_whole_day = itemView.findViewById(R.id.edittext_is_whole_day);
            editText_is_completed = itemView.findViewById(R.id.edittext_is_completed);
            editText_completed_at = itemView.findViewById(R.id.edittext_completed_at);
            editText_description = itemView.findViewById(R.id.edittext_description);

            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}