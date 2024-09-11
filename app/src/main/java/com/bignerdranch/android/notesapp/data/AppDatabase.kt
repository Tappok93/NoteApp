package com.bignerdranch.android.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BaseNote::class, BaseTask::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDatabase(): AppDatabase
    abstract fun getBaseDao(): BaseDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "database_app"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}