package com.bignerdranch.android.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "TableTask")
data class BaseTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameTask: String
)