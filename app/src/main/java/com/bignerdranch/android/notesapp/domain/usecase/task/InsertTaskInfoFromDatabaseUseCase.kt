package com.bignerdranch.android.notesapp.domain.usecase.task

import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class InsertTaskInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     * Вставка задачи
     */
    suspend fun insertTaskInDatabaseUseCase(info: TaskEntity) {
        repository.insertTaskInDatabase(info)
    }
}