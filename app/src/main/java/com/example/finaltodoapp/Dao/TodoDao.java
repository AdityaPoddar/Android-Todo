package com.example.finaltodoapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finaltodoapp.Model.Todo;

import java.util.List;
@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo_database")
    LiveData<List<Todo>> getAllTodo();

    @Insert
    void insertTodo(Todo... todo);


    @Query("DELETE FROM todo_database WHERE id=:id")
    void deleteTodo(int id);

    @Update
    void updateTodo(Todo todo);
}
