package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class GetOneTaskInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {
    /**
     * Получение одной задачи
     */
    suspend fun getOneTaskFromDatabaseUseCase(id: Int): TaskEntity? {
        return repositoryImpl.getTaskById(id = id)
    }
}