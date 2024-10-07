package com.bignerdranch.android.notesapp.domain.usecase.note

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class InsertNoteInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     * Вставка заметки
     */
    suspend fun insertNoteInDatabaseUseCase(infoNote: NoteEntity) {
        repository.insertNoteInDatabase(infoNote)
    }
}