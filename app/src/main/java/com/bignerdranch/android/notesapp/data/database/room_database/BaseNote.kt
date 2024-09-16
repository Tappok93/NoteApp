package com.bignerdranch.android.notesapp.data.database.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "TableNote")
data class BaseNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameNote: String,
    val date: Date
)