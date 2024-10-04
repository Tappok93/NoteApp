package com.bignerdranch.android.notesapp.domain.usecase.task

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class UpdateTaskInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     *  Обновление задачи
     */
    suspend fun updateTaskInDatabaseUseCase(nameTask: String, id: Int) {
        repository.updateTask(nameTask, id)
    }
}