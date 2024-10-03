package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import dagger.internal.DaggerCollections
import javax.inject.Inject

class CheckCompletionTaskUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {
    /**
     *  Обновление параметра проверки у задачи
     */
    suspend fun updateTaskCheckUseCase(checkTask: Boolean, id: Int) {
        repositoryImpl.updateTaskCheck(checkTask, id)
    }
}