package com.example.todo_app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.data.Todo
import com.example.todo_app.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class TodoViewModel(
    private val todoRepository: TodoRepository = Graph.todoRepository
) : ViewModel() {

    private val _todoList = mutableStateOf<List<Todo>>(emptyList())
    val todoList get() = _todoList.value

    lateinit var getAllTodo: Flow<List<Todo>>

    init {
        viewModelScope.launch {
            todoRepository.getAll().collect({ result ->
                _todoList.value = result
            });
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.add(todo)
        }
    }


    fun searchByTitle(title: String) {
        viewModelScope.launch {
            todoRepository.findByTitle(title).collect { result ->
                _todoList.value = result
            }
        }
    }

    fun getById(id: Long): Flow<Todo> {
        return todoRepository.getById(id)
    }

    fun update(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(todo)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todo)
            getAllTodo = todoRepository.getAll()
        }
    }
}