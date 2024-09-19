package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetListTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetOneTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertTaskInfoFromDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskViewModel : ViewModel() {
    private val repositoryDatabase = DatabaseRepositoryImpl()
    private val deleteTaskInfoFromDatabaseUseCase =
        DeleteTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private val getTaskInfoFromDatabaseUseCase =
        GetListTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private val insertTaskInfoFromDatabaseUseCase =
        InsertTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private val getOneTaskInfoFromDatabaseUseCase =
        GetOneTaskInfoFromDatabaseUseCase(repositoryDatabase)

    /**
     * Получение списка задач из базы данных
     */
    val allTasks: LiveData<List<TaskEntity>> =
        getTaskInfoFromDatabaseUseCase.getTaskFromDatabaseUseCase()

    /**
     * Метод вставки задачи в базу
     */
    suspend fun insertTask(task: TaskEntity) = withContext(Dispatchers.IO) {
        insertTaskInfoFromDatabaseUseCase.insertTaskInDatabaseUseCase(task)
    }

    /**
     * Метод удаления задачи из базы
     */
    suspend fun deleteTaskById(id: Int) = withContext(Dispatchers.IO) {
        deleteTaskInfoFromDatabaseUseCase.deleteTaskInDatabaseUseCase(id)
    }

    /**
     * Создание объекта задача
     */
    fun createTaskObj(nameTask: String): TaskEntity {
        val taskObj = TaskEntity(id = 0, nameTask = nameTask)
        return taskObj
    }

    /**
     * Получение объекта заметка по Id
     */
    suspend fun getObjectTaskById(id: Int): TaskEntity? {
        return getOneTaskInfoFromDatabaseUseCase.getOneTaskFromDatabaseUseCase(id = id)
    }

}