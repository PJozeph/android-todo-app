package com.example.todo_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="todo-table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="todo-title")
    val title: String="",
    @ColumnInfo(name="todo-desc")
    val description:String=""
)