package com.bignerdranch.android.notesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.notesapp.data.storage.room_database.AppDatabase
import com.bignerdranch.android.notesapp.data.storage.room_database.BaseDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database_app"
        ).build()
    }

    @Provides
    fun provideBaseDao(appDatabase: AppDatabase): BaseDao {
        return appDatabase.getBaseDao()
    }
}