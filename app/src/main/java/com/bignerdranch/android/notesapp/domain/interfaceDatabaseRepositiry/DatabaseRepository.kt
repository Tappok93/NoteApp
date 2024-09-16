package com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.database.room_database.BaseTask

interface DatabaseRepository {
    suspend fun insertNoteInDatabase(infoNote: BaseNote)
    suspend fun insertTaskInDatabase(infoTask: BaseTask)
    suspend fun deleteNoteInDatabase(nameNote: BaseNote)
    suspend fun deleteTaskInDatabase(nameTask: BaseTask)
    fun getNoteFromDatabase(): LiveData<List<BaseNote>>
    fun getTaskFromDatabase(): LiveData<List<BaseTask>>
}