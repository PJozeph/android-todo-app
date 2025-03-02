package com.example.todo_app

sealed class Screen(val route:String) {
    object HomeScreen: Screen("home_screen")
    object AddEditScreen: Screen("add_edit_screen")
}