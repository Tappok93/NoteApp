package com.bignerdranch.android.notesapp.data.database.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity

@Database(entities = [NoteEntity::class, TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
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

//Dependence ingaction - посмотреть