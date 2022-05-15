package com.example.finaltodoapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finaltodoapp.Model.Todo;
import com.example.finaltodoapp.R;
import com.example.finaltodoapp.ViewModel.TodoViewModel;
import com.example.finaltodoapp.databinding.ActivityInsertTodoBinding;
import com.example.finaltodoapp.databinding.ActivityUpdateTodoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateTodo extends AppCompatActivity {


    ActivityUpdateTodoBinding binding;
    String priority="1";
    String stitle,stodo,spriority;
    int id;
    TodoViewModel todoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todoViewModel= ViewModelProviders.of(this).get(TodoViewModel.class);

        id=getIntent().getIntExtra("id",0);
        stitle=getIntent().getStringExtra("title");
        stodo=getIntent().getStringExtra("todo");
        spriority=getIntent().getStringExtra("priority");

        binding.updateTitle.setText(stitle);
        binding.updateTodo.setText(stodo);

        if(spriority.equals("1"))
        {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
         else if(spriority.equals("2"))
        {
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
        else if(spriority.equals("3"))
        {
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }


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

        binding.updateTodoBtn.setOnClickListener( v->{

            String title=binding.updateTitle.getText().toString();
            String todo=binding.updateTodo.getText().toString();

            UpdateTodos(title,todo);

        });

    }

    private void UpdateTodos(String title, String todo) {
        Date date = new Date();
        CharSequence sequence= DateFormat.format("MMMM,d,yyyy",date.getTime());

        Todo todo2 = new Todo();
        todo2.id=id;
        todo2.todoTitle=title;
        todo2.todo=todo;
        todo2.todoDates=sequence.toString();
        todo2.todoPriority=priority;
        todoViewModel.updateTodos(todo2);

        Toast.makeText(this, "Todo Updated", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete)
        {
            BottomSheetDialog dialog=new BottomSheetDialog(UpdateTodo.this);


            View view = LayoutInflater.from(UpdateTodo.this).inflate(R.layout.delete_bottom,(LinearLayout) findViewById(R.id.bottomSheet));

            dialog.setContentView(view);

            TextView yes, no;
            yes=view.findViewById(R.id.delete_yes);
            no=view.findViewById(R.id.delete_no);


            yes.setOnClickListener(v ->
            {
                todoViewModel.deleteTodos(id);
                finish();
            });
            no.setOnClickListener(v ->
            {
                dialog.dismiss();
            });

            dialog.show();
        }
        return true;
    }
}