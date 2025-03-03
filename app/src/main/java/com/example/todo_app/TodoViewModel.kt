package com.example.todo_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.data.Todo
import com.example.todo_app.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class TodoViewModel(
    private val todoRepository: TodoRepository = Graph.todoRepository
): ViewModel() {


    lateinit var getAllTodo: Flow<List<Todo>>

    init {
        viewModelScope.launch {
            getAllTodo = todoRepository.getAll();
        }
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.add(todo)
        }
    }

    fun getById(id:Long): Flow<Todo> {
        return todoRepository.getById(id)
    }

    fun update(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(todo)
        }
    }

    fun delete(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todo)
            getAllTodo = todoRepository.getAll()
        }
    }
}