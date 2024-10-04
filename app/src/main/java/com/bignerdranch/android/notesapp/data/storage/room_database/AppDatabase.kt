package com.bignerdranch.android.notesapp.data.storage.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import javax.inject.Inject

@Database(entities = [NoteEntity::class, TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBaseDao(): BaseDao

//    companion object {
//        private var INSTANCE: AppDatabase? = null
//
//        @Inject
//        fun getInstance(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class.java) {
//                    INSTANCE = Room.databaseBuilder(
//                        context,
//                        AppDatabase::class.java,
//                        "database_app"
//                    ).build()
//                }
//            }
//            return INSTANCE
//        }
//    }
}