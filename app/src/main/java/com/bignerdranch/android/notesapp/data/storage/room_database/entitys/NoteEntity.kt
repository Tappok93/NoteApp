package com.bignerdranch.android.notesapp.data.storage.room_database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TableNote")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameNote: String,
    val nameHeaderNote: String,
    val date: String
)