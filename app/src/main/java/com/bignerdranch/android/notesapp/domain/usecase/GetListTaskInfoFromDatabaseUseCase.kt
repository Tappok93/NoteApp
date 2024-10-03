package com.bignerdranch.android.notesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class GetListTaskInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка задач
     */
    fun getTaskFromDatabaseUseCase(): LiveData<List<TaskEntity>> {
        return repositoryImpl.getTaskFromDatabase()
    }
}