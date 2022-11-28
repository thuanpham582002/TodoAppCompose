package com.example.todolist.ui.addedittodo

sealed class AddEditTodoEvent {
    data class EnteredTitle(val title: String) : AddEditTodoEvent()
    data class EnteredDescription(val description: String) : AddEditTodoEvent()
    object SaveTodo : AddEditTodoEvent()
}