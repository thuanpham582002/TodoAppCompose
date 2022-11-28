package com.example.todolist

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.addedittodo.AddEditTodoScreen
import com.example.todolist.ui.component.TodoTopBar
import com.example.todolist.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: "HomeScreen"
    var isSavable by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TodoTopBar(
                canNavigate = navController.previousBackStackEntry != null,
                title = currentScreen,
                navigate = {
                    navController.navigateUp()
                }
            )
        },
        floatingActionButton = {
            TodoFab(
                onClickTodoFabAdd = {
                    navController.navigate("AddTodoScreen")
                    Log.i(
                        "onClickTodoFabSave",
                        "TodoScreen: avController.navigate(\"AddTodoScreen\")"
                    )
                },
                onClickTodoFabSave = {
                    isSavable = true
                },
                currentScreen = currentScreen,
            )
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "HomeScreen",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable("HomeScreen") {
                    Log.i("backStackEntry", "$backStackEntry")
                    HomeScreen()
                }

                composable("AddTodoScreen") {
                    AddEditTodoScreen(
                        isSavable = isSavable,
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                        revertIsSavable = {
                            isSavable = false
                        },
                    )
                }
            }
        }
    )
}

@Composable
fun TodoFab(
    onClickTodoFabAdd: () -> Unit,
    onClickTodoFabSave: () -> Unit,
    currentScreen: String = "HomeScreen"
) {
    when (currentScreen) {
        "HomeScreen" -> {
            TodoFabAdd {
                onClickTodoFabAdd()
            }
        }

        "AddTodoScreen" -> {
            TodoFabSave {
                onClickTodoFabSave()
            }
        }
    }
}

@Composable
fun TodoFabSave(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Outlined.Save,
            contentDescription = "Save Todo"
        )
    }
}

@Composable
fun TodoFabAdd(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add Todo"
        )
    }
}

@Preview
@Composable
fun TodoPreview() {
    TodoScreen()
}