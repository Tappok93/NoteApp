package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class InsertNoteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Вставка заметки
     */
    suspend fun insertNoteInDatabaseUseCase(infoNote: NoteEntity) {
        repositoryImpl.insertNoteInDatabase(infoNote)
    }
}