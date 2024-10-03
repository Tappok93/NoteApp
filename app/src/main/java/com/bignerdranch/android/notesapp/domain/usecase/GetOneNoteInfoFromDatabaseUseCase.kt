package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class GetOneNoteInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {
    /**
     * Получение одной заметки
     */
    suspend fun getOneNoteFromDatabaseUseCase(id: Int): NoteEntity? {
        return repositoryImpl.getNoteById(id = id)
    }
}