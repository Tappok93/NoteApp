package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class UpdateTaskInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     *  Обновление задачи
     */
    suspend fun updateTaskInDatabaseUseCase(nameTask: String, id: Int) {
        repositoryImpl.updateTask(nameTask, id)
    }
}