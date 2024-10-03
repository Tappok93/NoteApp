package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class InsertNoteInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Вставка заметки
     */
    suspend fun insertNoteInDatabaseUseCase(infoNote: NoteEntity) {
        repositoryImpl.insertNoteInDatabase(infoNote)
    }
}