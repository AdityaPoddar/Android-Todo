package com.example.finaltodoapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltodoapp.Activity.UpdateTodo;
import com.example.finaltodoapp.MainActivity;
import com.example.finaltodoapp.Model.Todo;
import com.example.finaltodoapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.todoViewHolder> {

    MainActivity mainActivity;
    List<Todo> todos;
    List<Todo> allTodo;

    public TodoAdapter(MainActivity mainActivity, List<Todo> todos) {
        this.mainActivity = mainActivity;
        this.todos = todos;
        allTodo=new ArrayList<>(todos);

    }

        public void searchTodo(List<Todo> filter)
        {
            this.todos=filter;
            notifyDataSetChanged();
        }

    @Override
    public TodoAdapter.todoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new todoViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.todo_item, parent, false));
    }

    @Override
    public void onBindViewHolder( TodoAdapter.todoViewHolder holder, int position) {

        Todo todo2 = todos.get(position);

        if (todo2.todoPriority.equals("1")) {
            holder.todoPriority.setBackgroundResource(R.drawable.green_priority);
        } else if (todo2.todoPriority.equals("2")) {
            holder.todoPriority.setBackgroundResource(R.drawable.yellow_priority);
        } else if (todo2.todoPriority.equals("3")) {
            holder.todoPriority.setBackgroundResource(R.drawable.red_priority);
        }

        holder.title.setText(todo2.todoTitle);
        holder.todo.setText(todo2.todo);
        holder.date.setText(todo2.todoDates);

        holder.itemView.setOnClickListener(v ->
        {
            Intent intent = new Intent(mainActivity, UpdateTodo.class);
            intent.putExtra("id",todo2.id);
            intent.putExtra("title",todo2.todoTitle);
            intent.putExtra("todo",todo2.todo);
            intent.putExtra("priority",todo2.todoPriority);
            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class todoViewHolder extends RecyclerView.ViewHolder {

        TextView title, todo, date;
        View todoPriority;

        public todoViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todotitle);
            todo = itemView.findViewById(R.id.todoss);
            date = itemView.findViewById(R.id.tododate);
            todoPriority = itemView.findViewById(R.id.todoPriority);
        }
    }
}
