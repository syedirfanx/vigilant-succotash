package com.example.vigilantsuccotash;

public class TaskModelClass {

    Integer task_id;
    float start_at;
    float end_at;
    float dated;
    Integer is_whole_day;
    Integer is_completed;
    float completed_at;
    String description;

    public TaskModelClass(
    float start_at,
    float end_at,
    float dated,
    int is_whole_day,
    int is_completed,
    float completed_at,
    String description) {
        this.start_at = start_at;
        this.end_at = end_at;
        this.dated = dated;
        this.is_whole_day = is_whole_day;
        this.is_completed = is_completed;
        this.completed_at = completed_at;
        this.description = description;
    }

    public TaskModelClass(Integer task_id, float start_at, float end_at, float dated, Integer is_whole_day, Integer is_completed, float completed_at, String description) {
        this.task_id = task_id;
        this.start_at = start_at;
        this.end_at = end_at;
        this.dated = dated;
        this.is_whole_day = is_whole_day;
        this.is_completed = is_completed;
        this.completed_at = completed_at;
        this.description = description;
    }

    public Integer getTaskID() {
        return task_id;
    }

    public void setTaskID(Integer task_id) {
        this.task_id = task_id;
    }

    public float getStartAt() {
        return start_at;
    }

    public void setStartAt(float start_at) {
        this.start_at = start_at;
    }

    public float getEndAt() {
        return end_at;
    }

    public void setEndAt(float end_at) {
        this.end_at = end_at;
    }

    public float getDated() {
        return dated;
    }

    public void setDated(float dated) {
        this.dated = dated;
    }

    public Integer getIsWholeDay() {
        return is_whole_day;
    }

    public void setIsWholeDay(Integer is_whole_day) {
        this.is_whole_day = is_whole_day;
    }

    public Integer getIsCompleted() {
        return is_completed;
    }

    public void setIsCompleted(Integer is_completed) {
        this.is_completed = is_completed;
    }

    public float getCompletedAt() {
        return completed_at;
    }

    public void setCompletedAt(float completed_at) {
        this.completed_at = completed_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
