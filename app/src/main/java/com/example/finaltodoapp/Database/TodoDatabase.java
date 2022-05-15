package com.example.finaltodoapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finaltodoapp.Dao.TodoDao;
import com.example.finaltodoapp.Model.Todo;

@Database(entities = {Todo.class},version= 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    public static TodoDatabase INSTANCE;

    public static TodoDatabase getDatabaseInstance(Context context )
    {
        if(INSTANCE == null)
        {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), TodoDatabase.class, "Todo_Database").allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }
}
