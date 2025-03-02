package com.example.todo_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo_app.TodoViewModel

@Composable
fun TodoListView(
    viewModel: TodoViewModel,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val todoList by viewModel.getAllTodo.collectAsState(initial = emptyList())
        LazyColumn {
            items(todoList) { todo ->
                TodoItem(todo, navHostController)
            }
        }
    }
}