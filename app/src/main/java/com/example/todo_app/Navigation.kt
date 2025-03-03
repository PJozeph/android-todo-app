package com.example.todo_app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo_app.view.AddEditView
import com.example.todo_app.view.TodoListView

@Composable
fun Navigation(
    viewModel: TodoViewModel,
    navController: NavHostController,
    innerPaddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,

    ) {
        composable(Screen.HomeScreen.route) {
            TodoListView(viewModel, navController, innerPaddingValues)
        }

        composable(Screen.AddEditScreen.route+"/{id}") {
            val id = navController.currentBackStackEntry?.arguments?.getString("id")
            val todoFlow = viewModel.getById(id?.toLongOrNull() ?: 0)
            val todo by todoFlow.collectAsState(initial = null)
            AddEditView(viewModel, todo, navController)
        }
    }
}