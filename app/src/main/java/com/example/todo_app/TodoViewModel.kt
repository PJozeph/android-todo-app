package com.example.todo_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


    fun onWishTitleChanged(newString:String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }

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

    fun update(wish: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(wish= wish)
        }
    }

    fun deleteWish(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todo)
            getAllTodo = todoRepository.getAll()
        }
    }
}