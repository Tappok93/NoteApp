package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class GetOneNoteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {
    /**
     * Получение одной заметки
     */
    suspend fun getOneNoteFromDatabaseUseCase(id: Int): NoteEntity? {
        return repositoryImpl.getNoteById(id = id)
    }
}