package com.example.finaltodoapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.finaltodoapp.Model.Todo;
import com.example.finaltodoapp.Repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    public TodoRepository repository;
    public LiveData<List<Todo>> getallTodo;
    public TodoViewModel( Application application) {
        super(application);

        repository=new TodoRepository(application);
        getallTodo=repository.getallTodo;
    }

   public void insertTodos(Todo todo)
    {
        repository.insertTodo(todo);
    }
    public void deleteTodos(int id)
    {
        repository.deleteTodo(id);
    }
   public void updateTodos(Todo todo)
    {
        repository.updateTodo(todo);
    }
}
