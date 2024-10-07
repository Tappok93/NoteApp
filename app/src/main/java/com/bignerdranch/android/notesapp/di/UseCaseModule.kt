package com.bignerdranch.android.notesapp.di

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {
    /**
     * //Создаём для Dagger 2 экземпляр класса DatabaseRepositoryImpl
     */
    @Binds
    @Singleton
    abstract fun provideDatabaseRepository(repository: DatabaseRepositoryImpl): DatabaseRepository
}