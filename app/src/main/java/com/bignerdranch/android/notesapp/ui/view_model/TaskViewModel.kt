package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertTaskInfoFromDatabaseUseCase
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repositoryDatabase = DatabaseRepositoryImpl()
    private val deleteTaskInfoFromDatabaseUseCase =
        DeleteTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private var getTaskInfoFromDatabaseUseCase = GetTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private var insertTaskInfoFromDatabaseUseCase =
        InsertTaskInfoFromDatabaseUseCase(repositoryDatabase)

    /**
     * Получение списка задач из базы данных
     */
    val allTasks: LiveData<List<TaskEntity>> =
        getTaskInfoFromDatabaseUseCase.getTaskFromDatabaseUseCase()

    /**
     * Метод вставки задачи в базу
     */
    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        insertTaskInfoFromDatabaseUseCase.insertTaskInDatabaseUseCase(task)
    }

    /**
     * Метод удаления задачи из базы
     */
    fun deleteTaskByName(nameTask: TaskEntity) = viewModelScope.launch {
        deleteTaskInfoFromDatabaseUseCase.deleteTaskInDatabaseUseCase(nameTask)
    }

    /**
     * Создание объекта задача
     */
    fun createTaskObj(nameTask: String): TaskEntity {
        val taskObj = TaskEntity(id = 0, nameTask = nameTask)
        return taskObj
    }

}