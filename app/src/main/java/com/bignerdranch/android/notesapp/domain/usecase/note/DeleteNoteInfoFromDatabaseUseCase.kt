package com.bignerdranch.android.notesapp.domain.usecase.note

import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class DeleteNoteInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     *  Удаления заметки
     */
    suspend fun deleteNoteInDatabaseUseCase(id: Int) {
        repository.deleteByIdNoteInDatabase(id)
    }
}