package com.bignerdranch.android.notesapp.domain.usecase.note

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class GetOneNoteInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {
    /**
     * Получение одной заметки
     */
    suspend fun getOneNoteFromDatabaseUseCase(id: Int): NoteEntity? {
        return repository.getNoteById(id = id)
    }
}