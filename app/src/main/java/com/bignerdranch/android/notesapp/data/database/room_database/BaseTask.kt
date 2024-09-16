package com.bignerdranch.android.notesapp.data.database.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TableTask")
data class BaseTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameTask: String
)