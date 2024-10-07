package com.bignerdranch.android.notesapp.domain.usecase.note

import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class UpdateNoteInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     *  Обновление заметки
     */
    suspend fun updateNoteInDatabaseUseCase(nameNote: String, id: Int) {
        repository.updateNote(nameNote, id)
    }
}