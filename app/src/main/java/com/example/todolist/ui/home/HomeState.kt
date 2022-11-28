package com.example.todolist.ui.home

import com.example.core.data.source.local.model.todo.TodoEntity

data class HomeState(
    val listTodoEntity: List<TodoEntity> = emptyList()
)