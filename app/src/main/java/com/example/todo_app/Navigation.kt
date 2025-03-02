package com.example.todo_app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(viewModel: TodoViewModel,
               navController: NavHostController,
               innerPaddingValues: PaddingValues
){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            TodoListView(viewModel)
        }

        composable(Screen.AddScreen.route){
            AddEditView(viewModel)
        }
    }
}