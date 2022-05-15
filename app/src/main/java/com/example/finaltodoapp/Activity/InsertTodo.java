package com.example.finaltodoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.finaltodoapp.Model.Todo;
import com.example.finaltodoapp.R;
import com.example.finaltodoapp.ViewModel.TodoViewModel;
import com.example.finaltodoapp.databinding.ActivityInsertTodoBinding;

import java.util.Date;

public class InsertTodo extends AppCompatActivity {

    ActivityInsertTodoBinding binding;
    String title , todo;
    TodoViewModel todoViewModel;
    String priority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todoViewModel= ViewModelProviders.of(this).get(TodoViewModel.class);


        binding.doneTodoBtn.setOnClickListener(v ->
        {
            title=binding.todoTitle.getText().toString();
            todo=binding.todos.getText().toString();

            CreateTodo(title,todo);

        });
        binding.redPriority.setOnClickListener(v ->
        {
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            priority="3";
        });
        binding.yellowPriority.setOnClickListener(v ->
        {
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority="2";
        });
        binding.greenPriority.setOnClickListener(v ->
        {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            priority="1";
        });
    }

    private void CreateTodo(String title, String todo) {

        Date date = new Date();
        CharSequence sequence= DateFormat.format("MMMM,d,yyyy",date.getTime());
        Todo todo1 = new Todo();
        todo1.todoTitle=title;
        todo1.todo=todo;
        todo1.todoDates=sequence.toString();
        todo1.todoPriority=priority;
        todoViewModel.insertTodos(todo1);

        Toast.makeText(this, "Todo Created", Toast.LENGTH_SHORT).show();
        finish();
    }
}