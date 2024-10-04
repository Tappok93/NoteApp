package com.bignerdranch.android.notesapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

//    @Provides
//    @Singleton
//    fun provideApplication(): Application {
//        return application
//    }
}