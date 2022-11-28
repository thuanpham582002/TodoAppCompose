package com.example.todolist.di

import com.example.core.data.source.local.TodoRepositoryImpl
import com.example.core.data.source.local.model.TodoDatabase
import com.example.core.domain.TodoRepository
import com.example.core.domain.use_case.TodoUseCase
import com.example.core.domain.use_case.TodoUseCaseImpl
import com.example.todolist.App

object AppInject {
    fun provideToDoRepository(): TodoRepositoryImpl {
        val db = TodoDatabase.getInstance(App.instance.applicationContext)
        return TodoRepositoryImpl.getInstance(db.todoDao)
    }

    fun provideToDoUseCases(): TodoUseCase {
        return TodoUseCaseImpl(provideToDoRepository())
    }
}