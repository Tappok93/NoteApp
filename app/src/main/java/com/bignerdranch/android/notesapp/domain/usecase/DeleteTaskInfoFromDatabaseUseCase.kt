package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class DeleteTaskInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     *  Удаления задачи
     */
    suspend fun deleteTaskInDatabaseUseCase(id: Int) {
        repositoryImpl.deleteByIdTaskInDatabase(id)
    }
}