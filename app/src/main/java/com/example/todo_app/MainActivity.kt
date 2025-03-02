package com.example.todo_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todo_app.drawer.Drawer
import com.example.todo_app.ui.theme.Todo_appTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val controller: NavHostController = rememberNavController()
            val todoViewModel: TodoViewModel = viewModel()

            val navBackStackEntry by controller.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Todo_appTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Mini Todo App")
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) {
                                        drawerState.open()
                                    } else {
                                        drawerState.close()
                                    }
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                    floatingActionButton = {
                        if (currentRoute == Screen.HomeScreen.route) { // Replace with your route name
                            IconButton(
                                onClick = { controller.navigate(Screen.AddEditScreen.route + "/0") }
                            ) {
                                Icon(
                                    Icons.Filled.AddCircle,
                                    modifier = Modifier.size(100.dp),
                                    contentDescription = "Add Todo",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Drawer(
                        drawerState = drawerState,
                        scope = scope,
                        navController = controller,
                        todoViewModel = todoViewModel,
                        innerPaddingValues = innerPadding
                    )

                }
            }
        }
    }
}