package com.bignerdranch.android.notesapp

import android.app.Application
import android.content.Context
import com.bignerdranch.android.notesapp.di.AppComponent
import com.bignerdranch.android.notesapp.di.DaggerAppComponent
import com.bignerdranch.android.notesapp.di.UseCaseModule

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    companion object {
        private lateinit var instance: MyApplication

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeDagger()

    }

    /**
     * Метод инициализации Dagger2
     */
    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .useCaseModule(UseCaseModule())
            .build()
    }
}