package com.example.finaltodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.finaltodoapp.Activity.InsertTodo;
import com.example.finaltodoapp.Adapter.TodoAdapter;
import com.example.finaltodoapp.Model.Todo;
import com.example.finaltodoapp.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton newTodoBtn;
    TodoViewModel todoViewModel;
    RecyclerView todoRecycler;
    TodoAdapter adapter;
    List<Todo> FilterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoViewModel= ViewModelProviders.of(this).get(TodoViewModel.class);

        newTodoBtn=findViewById(R.id.newTodoBtn);
        todoRecycler=findViewById(R.id.todoRecycler);
        newTodoBtn.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, InsertTodo.class));
        });
        todoViewModel.getallTodo.observe(this,todos ->
        {
            todoRecycler.setLayoutManager(new LinearLayoutManager(this));
            adapter=new TodoAdapter(MainActivity.this,todos);
            todoRecycler.setAdapter(adapter);
            FilterList= todos;
//            for search
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                TodoFilters(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void TodoFilters(String s) {
        ArrayList<Todo> FilterName= new ArrayList<>();

        for (Todo todo:this.FilterList) {
            if(todo.todoTitle.contains(s))
            {
                FilterName.add(todo);
            }
            
        }
        this.adapter.searchTodo(FilterName);

    }
}