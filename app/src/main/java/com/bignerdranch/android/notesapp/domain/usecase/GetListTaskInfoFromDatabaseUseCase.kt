package com.bignerdranch.android.notesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class GetListTaskInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка задач
     */
    fun getTaskFromDatabaseUseCase(): LiveData<List<TaskEntity>> {
        return repositoryImpl.getTaskFromDatabase()
    }
}