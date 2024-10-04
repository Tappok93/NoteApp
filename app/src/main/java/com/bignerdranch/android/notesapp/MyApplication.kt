package com.bignerdranch.android.notesapp

import android.app.Application
import android.content.Context
import com.bignerdranch.android.notesapp.di.AppComponent
import com.bignerdranch.android.notesapp.di.AppModule
import com.bignerdranch.android.notesapp.di.DaggerAppComponent
import com.bignerdranch.android.notesapp.di.DatabaseModule
import javax.inject.Inject

class MyApplication: Application() {
    lateinit var appComponent: AppComponent

//    companion object {
//        private lateinit var instance: MyApplication
//
//        fun getAppContext(): Context {
//            return instance.applicationContext
//        }
//    }

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