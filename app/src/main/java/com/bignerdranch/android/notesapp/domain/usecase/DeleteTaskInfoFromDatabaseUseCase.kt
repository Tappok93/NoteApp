package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class DeleteTaskInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     *  Удаления задачи
     */
    suspend fun deleteTaskInDatabaseUseCase(id: Int) {
        repositoryImpl.deleteByIdTaskInDatabase(id)
    }
}