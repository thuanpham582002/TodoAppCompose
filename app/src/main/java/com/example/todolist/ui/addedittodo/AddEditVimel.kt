package com.example.todolist.ui.addedittodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.local.model.todo.TodoEntity
import com.example.todolist.di.AppInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddEditVimel : ViewModel() {
    private val _state = MutableStateFlow(AddEditTodoState())
    val state = _state.asStateFlow()
    private val todoUseCase = AppInject.provideToDoUseCases()

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.EnteredTitle -> {
                _state.value = _state.value.copy(title = event.title)
            }

            is AddEditTodoEvent.EnteredDescription -> {
                _state.value = _state.value.copy(description = event.description)
            }

            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    todoUseCase.insertTodoEntity(
                        TodoEntity(
                            title = _state.value.title,
                            description = _state.value.description
                        )
                    )
                }
            }
        }
    }
}