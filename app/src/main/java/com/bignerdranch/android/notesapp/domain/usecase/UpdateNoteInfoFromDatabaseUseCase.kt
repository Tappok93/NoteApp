package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class UpdateNoteInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     *  Обновление заметки
     */
    suspend fun updateNoteInDatabaseUseCase(nameNote: String, id: Int) {
        repositoryImpl.updateNote(nameNote, id)
    }
}