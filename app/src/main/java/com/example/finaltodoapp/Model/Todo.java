package com.example.finaltodoapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Todo_Database")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "todo_title")
    public String todoTitle;

    @ColumnInfo(name = "todo_date")
    public String todoDates;

    @ColumnInfo(name = "todoApp")
    public String todo;

    @ColumnInfo(name = "todo_priority")
    public  String todoPriority;
}
