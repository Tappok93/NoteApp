package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class CheckCompletionTaskUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {
    /**
     *  Обновление параметра проверки у задачи
     */
    suspend fun updateTaskCheckUseCase(checkTask: Boolean, id: Int) {
        repositoryImpl.updateTaskCheck(checkTask, id)
    }
}