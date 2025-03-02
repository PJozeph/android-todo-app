package com.example.todo_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_app.TodoViewModel
import com.example.todo_app.data.Todo

@Composable
fun AddEditView(
    viewModel: TodoViewModel?,
    todo: Todo?,
    navHostController: NavHostController
) {

    val title = remember {
        mutableStateOf(todo?.title ?: "")
    }

    val description = remember {
        mutableStateOf(todo?.description ?: "")
    }

    LaunchedEffect(todo) {
        title.value = todo?.title ?: ""
        description.value = todo?.description ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            title.value,
            {
                title.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )
        TextField(description.value, {
            description.value = it
        }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navHostController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    if (todo != null) {
                        viewModel?.update(
                            Todo(
                                id = todo.id,
                                title = title.value,
                                description = description.value
                            )
                        )
                    } else {
                        viewModel?.addTodo(
                            Todo(
                                title = title.value,
                                description = description.value
                            )
                        )
                    }
                    navHostController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("Save")
            }
        }
    }
}

@Preview
@Composable
fun AddEditViewPreview(
    showBackgroundView: Boolean = true
) {
    AddEditView(
        viewModel = null,
        todo = null,
        navHostController = rememberNavController()
    )
}