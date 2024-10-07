package com.bignerdranch.android.notesapp.domain.usecase.task

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class GetListTaskInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     * Получение списка задач
     */
    fun getTaskFromDatabaseUseCase(): LiveData<List<TaskEntity>> {
        return repository.getTaskFromDatabase()
    }
}