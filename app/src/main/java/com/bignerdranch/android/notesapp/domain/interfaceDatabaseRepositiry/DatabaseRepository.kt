package com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity

interface DatabaseRepository {
    suspend fun insertNoteInDatabase(infoNote: NoteEntity)
    suspend fun insertTaskInDatabase(infoTask: TaskEntity)
    suspend fun deleteNoteInDatabase(nameNote: NoteEntity)
//    suspend fun deleteByNameNoteInDatabase(nameNote: String)
    suspend fun deleteTaskInDatabase(nameTask: TaskEntity)
    fun getNoteFromDatabase(): LiveData<List<NoteEntity>>
    fun getTaskFromDatabase(): LiveData<List<TaskEntity>>
}