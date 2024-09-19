package com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity

interface DatabaseRepository {
    suspend fun insertNoteInDatabase(infoNote: NoteEntity)
    suspend fun insertTaskInDatabase(infoTask: TaskEntity)
    suspend fun deleteByIdNoteInDatabase(id: Int)
    suspend fun deleteByIdTaskInDatabase(id: Int)
    suspend fun getNoteById(id: Int): NoteEntity?
    suspend fun getTaskById(id: Int): TaskEntity?
    fun getNoteFromDatabase(): LiveData<List<NoteEntity>>
    fun getTaskFromDatabase(): LiveData<List<TaskEntity>>
}