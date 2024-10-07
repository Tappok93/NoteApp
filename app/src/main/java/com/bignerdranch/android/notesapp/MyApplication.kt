package com.bignerdranch.android.notesapp

import android.app.Application
import com.bignerdranch.android.notesapp.di.AppComponent
import com.bignerdranch.android.notesapp.di.AppModule
import com.bignerdranch.android.notesapp.di.DaggerAppComponent
import com.bignerdranch.android.notesapp.di.DatabaseModule

class MyApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    /**
     * Метод инициализации Dagger2
     */
    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .build()
    }
}