package com.bignerdranch.android.notesapp.di

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.notesapp.data.storage.room_database.AppDatabase
import com.bignerdranch.android.notesapp.data.storage.room_database.BaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    /**
     * //Создаём для Dagger 2 инициализацию Database
     */
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database_app"
        ).build()
    }

    /**
     * //Создаём для Dagger 2 экземпляр интерфейса BaseDao
     */
    @Provides
    fun provideBaseDao(appDatabase: AppDatabase): BaseDao {
        return appDatabase.getBaseDao()
    }
}