package com.bignerdranch.android.notesapp.data.database.room_database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TableTask")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameTask: String
)