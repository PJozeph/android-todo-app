package com.example.todo_app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Navigation(viewModel: TodoViewModel = viewModel(),
               navController: NavHostController,
               innerPaddingValues: PaddingValues
){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            TodoListView()
        }

        composable(Screen.AddScreen.route){
            AddEditView()
        }


    }
}