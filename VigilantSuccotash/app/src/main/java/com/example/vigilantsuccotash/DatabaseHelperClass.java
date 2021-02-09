package com.example.vigilantsuccotash;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo_sql";
    private static final String TABLE_NAME = "TASK";
    public static final String TaskID = "task_id";
    public static final String StartAt = "start_at";
    public static final String EndAt = "end_at";
    public static final String Dated = "dated";
    public static final String IsWholeDay = "is_whole_day";
    public static final String IsCompleted = "is_completed";
    public static final String CompletedAt = "completed_at";
    public static final String Description = "description";
    private SQLiteDatabase sqLiteDatabase;


    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+TaskID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + StartAt + " REAL NOT NULL,"+ EndAt + " REAL NOT NULL,"+Dated + " REAL NOT NULL,"+ IsWholeDay + " INTEGER NOT NULL,"+ IsCompleted + " INTEGER NOT NULL,"+ CompletedAt + " REAL NOT NULL,"+
            Description+" TEXT NOT NULL);";
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTask(TaskModelClass taskModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.StartAt, taskModelClass.getStartAt());
        contentValues.put(DatabaseHelperClass.EndAt, taskModelClass.getEndAt());
        contentValues.put(DatabaseHelperClass.Dated, taskModelClass.getDated());
        contentValues.put(DatabaseHelperClass.IsWholeDay, taskModelClass.getIsWholeDay());
        contentValues.put(DatabaseHelperClass.IsCompleted, taskModelClass.getIsCompleted());
        contentValues.put(DatabaseHelperClass.CompletedAt, taskModelClass.getCompletedAt());
        contentValues.put(DatabaseHelperClass.Description, taskModelClass.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<TaskModelClass> getTaskList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<TaskModelClass> storeTask = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int task_id = Integer.parseInt(cursor.getString(0));
                float started_at = Float.parseFloat(cursor.getString(1));
                float end_at = Float.parseFloat(cursor.getString(2));
                float dated = Float.parseFloat(cursor.getString(3));
                int is_whole_day = Integer.parseInt(cursor.getString(4));
                int is_completed = Integer.parseInt(cursor.getString(5));
                float completed_at = Float.parseFloat(cursor.getString(6));
                String description = cursor.getString(7);
                storeTask.add(new TaskModelClass(task_id, started_at, end_at, dated, is_whole_day, is_completed, completed_at, description));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeTask;
    }

    public void updateTask(TaskModelClass taskModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.StartAt, taskModelClass.getStartAt());
        contentValues.put(DatabaseHelperClass.EndAt, taskModelClass.getEndAt());
        contentValues.put(DatabaseHelperClass.Dated, taskModelClass.getDated());
        contentValues.put(DatabaseHelperClass.IsWholeDay, taskModelClass.getIsWholeDay());
        contentValues.put(DatabaseHelperClass.IsCompleted, taskModelClass.getIsCompleted());
        contentValues.put(DatabaseHelperClass.CompletedAt, taskModelClass.getCompletedAt());
        contentValues.put(DatabaseHelperClass.Description, taskModelClass.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,TaskID + " = ?" , new String[]
                {String.valueOf(taskModelClass.getTaskID())});
    }

    public void deleteTask(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TaskID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
