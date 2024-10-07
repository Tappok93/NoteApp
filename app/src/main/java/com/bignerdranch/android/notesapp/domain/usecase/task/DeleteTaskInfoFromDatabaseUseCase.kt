package com.bignerdranch.android.notesapp.domain.usecase.task

import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class DeleteTaskInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     *  Удаления задачи
     */
    suspend fun deleteTaskInDatabaseUseCase(id: Int) {
        repository.deleteByIdTaskInDatabase(id)
    }
}