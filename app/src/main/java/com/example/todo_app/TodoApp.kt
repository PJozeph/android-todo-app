package com.example.todo_app

import android.app.Application

class TodoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}