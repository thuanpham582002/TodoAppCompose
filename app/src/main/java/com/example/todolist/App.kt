package com.example.todolist

import android.app.Application
import com.example.todolist.di.AppInject

class App : Application() {
    companion object {
        lateinit var instance: App
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}