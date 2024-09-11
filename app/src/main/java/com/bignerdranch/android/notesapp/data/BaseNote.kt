package com.bignerdranch.android.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "TableNote")
data class BaseNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameNote: String,
    val date: Date
)