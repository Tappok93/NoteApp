package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class DeleteNoteInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     *  Удаления заметки
     */
    suspend fun deleteNoteInDatabaseUseCase(id: Int) {
        repositoryImpl.deleteByIdNoteInDatabase(id)
    }
}