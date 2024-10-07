package com.bignerdranch.android.notesapp.domain.usecase.task

import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class CheckCompletionTaskUseCase @Inject constructor(private val repository: DatabaseRepository) {
    /**
     *  Обновление параметра проверки у задачи
     */
    suspend fun updateTaskCheckUseCase(checkTask: Boolean, id: Int) {
        repository.updateTaskCheck(checkTask, id)
    }
}