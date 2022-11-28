package com.example.todolist.ui.addedittodo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTodoScreen(
    modifier: Modifier = Modifier,
    isSaveable: Boolean,
    onNavigateUp: () -> Unit,
    revertIsSaveable: () -> Unit,
) {
    val addEditVimel: AddEditVimel = viewModel()
    val state by addEditVimel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    if (isSaveable) {
        addEditVimel.onEvent(AddEditTodoEvent.SaveTodo)
        revertIsSaveable()
        onNavigateUp()
        Log.i("AddEditTodo", "AddEditTodoScreen: OnSave")
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = state.title,
            onValueChange = { addEditVimel.onEvent(AddEditTodoEvent.EnteredTitle(it)) },
            label = { Text(text = "Title") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus((FocusDirection.Down))
                }
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.description,
            onValueChange = { addEditVimel.onEvent(AddEditTodoEvent.EnteredDescription(it)) },
            label = { Text(text = "Description") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}