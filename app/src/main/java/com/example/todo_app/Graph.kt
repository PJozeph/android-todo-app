package com.example.todo_app

import android.content.Context
import androidx.room.Room
import com.example.todo_app.data.TodoDatabase
import com.example.todo_app.data.TodoRepository


object Graph {
    lateinit var database: TodoDatabase

    val todoRepository by lazy {
        TodoRepository(database.todoDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, TodoDatabase::class.java, "todolist.db").build()
    }
}
