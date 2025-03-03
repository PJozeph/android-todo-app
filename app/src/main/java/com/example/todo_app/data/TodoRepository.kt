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
    
    fun findByTitle(title: String): Flow<List<Todo>> {
        return dao.findByTitle(title)
    }

    suspend fun update(todo: Todo) {
        dao.update(todo)
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo)
    }
}