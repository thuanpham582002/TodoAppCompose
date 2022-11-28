package com.example.todolist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.util.TodoOrder
import com.example.todolist.di.AppInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeScreenVimel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    var job: Job? = null
    private val todoUseCase = AppInject.provideToDoUseCases()

    init {
        job = todoUseCase.getAllTodoEntity(TodoOrder.Order()).onEach {
            _state.value = _state.value.copy(listTodoEntity = it)
        }.launchIn(viewModelScope)
    }
}