package com.bignerdranch.android.notesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.BaseTask
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class GetTaskInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка задач
     */
    fun getTaskFromDatabaseUseCase(): LiveData<List<BaseTask>> {
        return repositoryImpl.getTaskFromDatabase()
    }
}