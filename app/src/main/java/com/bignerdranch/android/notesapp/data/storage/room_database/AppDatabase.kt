package com.bignerdranch.android.notesapp.data.storage.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity

@Database(entities = [NoteEntity::class, TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBaseDao(): BaseDao
}