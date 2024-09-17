package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class InsertTaskInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Вставка задачи
     */
    suspend fun insertTaskInDatabaseUseCase(info: TaskEntity) {
        repositoryImpl.insertTaskInDatabase(info)
    }
}