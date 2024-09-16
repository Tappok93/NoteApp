package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class InsertNoteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Вставка заметки
     */
    suspend fun insertNoteInDatabaseUseCase(infoNote: BaseNote) {
        repositoryImpl.insertNoteInDatabase(infoNote)
    }
}