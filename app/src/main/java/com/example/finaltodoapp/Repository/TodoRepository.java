package com.example.finaltodoapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.finaltodoapp.Dao.TodoDao;
import com.example.finaltodoapp.Database.TodoDatabase;
import com.example.finaltodoapp.Model.Todo;

import java.util.List;

public class TodoRepository {

    public TodoDao todoDao;
    public LiveData<List<Todo>> getallTodo;

    public TodoRepository(Application application)
    {
        TodoDatabase database = TodoDatabase.getDatabaseInstance(application);
        todoDao=database.todoDao();
        getallTodo=todoDao.getAllTodo();

    }

    public void insertTodo(Todo... todo)
    {
        todoDao.insertTodo(todo);
    }
    public void deleteTodo(int id)
    {
        todoDao.deleteTodo(id);
    }
    public void updateTodo(Todo todo)
    {
        todoDao.updateTodo(todo);
    }
}
