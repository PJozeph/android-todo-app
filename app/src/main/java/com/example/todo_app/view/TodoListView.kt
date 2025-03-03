package com.example.todo_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo_app.TodoViewModel

@Composable
fun TodoListView(
    viewModel: TodoViewModel,
    navHostController: NavHostController,
    innerPaddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        val search = remember {
            mutableStateOf("")
        }

        StyledTextField(search.value, {
            search.value = it;
            viewModel.searchByTitle(search.value)
        }, "Search")

        Spacer(modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(viewModel.todoList) { todo ->
                TodoItem(todo, navHostController, viewModel)
            }
        }
    }
}