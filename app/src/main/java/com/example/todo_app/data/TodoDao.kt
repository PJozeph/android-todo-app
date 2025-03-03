package com.example.todo_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun add(wishEntity: Todo)

    @Query("Select * from `todo-table`")
    abstract fun getAll(): Flow<List<Todo>>

    @Query("Select * from `todo-table` where `todo-title` like '%' || :title || '%'")
    abstract fun findByTitle(title: String): Flow<List<Todo>>

    @Update
    abstract suspend fun update(wishEntity: Todo)

    @Delete
    abstract suspend fun delete(wishEntity: Todo)

    @Query("Select * from `todo-table` where id=:id")
    abstract fun getById(id: Long): Flow<Todo>

}