package com.bignerdranch.android.notesapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    /**
     * //Создаём для Dagger 2 экземпляр Context
     */
    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }
}