package com.example.todo_app.data

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val dao: TodoDao) {

    suspend fun add(todo: Todo) {
        dao.add(todo)
    }

    fun getAll(): Flow<List<Todo>> = dao.getAll()

    fun getById(id: Long): Flow<Todo> {
        return dao.getById(id)
    }

    suspend fun update(wish: Todo) {
        dao.update(wish)
    }

    suspend fun delete(wish: Todo) {
        dao.delete(wish)
    }

}