package com.example.todo_app.view

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo_app.Screen
import com.example.todo_app.data.Todo

@Composable
fun TodoItem(todo: Todo, navHostController: NavHostController) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
        onClick = {
            navHostController.navigate(Screen.AddEditScreen.route + "/${todo.id}")
        }
    ) {
        Text(text = todo.title, modifier = Modifier.padding(8.dp))
        Text(text = todo.description, modifier = Modifier.padding(8.dp))
    }
}