package com.bignerdranch.android.notesapp.domain.usecase.task

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class GetOneTaskInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {
    /**
     * Получение одной задачи
     */
    suspend fun getOneTaskFromDatabaseUseCase(id: Int): TaskEntity? {
        return repository.getTaskById(id = id)
    }
}