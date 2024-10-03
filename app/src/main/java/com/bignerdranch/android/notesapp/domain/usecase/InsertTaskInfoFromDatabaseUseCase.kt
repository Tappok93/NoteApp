package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class InsertTaskInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Вставка задачи
     */
    suspend fun insertTaskInDatabaseUseCase(info: TaskEntity) {
        repositoryImpl.insertTaskInDatabase(info)
    }
}