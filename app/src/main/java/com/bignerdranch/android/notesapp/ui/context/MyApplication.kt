package com.bignerdranch.android.notesapp.ui.context

import android.app.Application
import android.content.Context
import android.util.Log

class MyApplication : Application() {
    companion object {
        private lateinit var instance: MyApplication

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d("MyActivity", "onCreate() вызван")
    }
}