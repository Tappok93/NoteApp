package com.bignerdranch.android.notesapp.ui.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.CheckCompletionTaskUseCase
import com.bignerdranch.android.notesapp.domain.usecase.DeleteTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetListTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetOneTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.UpdateTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.utils.UtilsApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    private val updateTaskInfoFromDatabaseUseCase =
        UpdateTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private val checkCompletionTaskUseCase =
        CheckCompletionTaskUseCase(repositoryDatabase)

    /**
     * Получение списка задач из базы данных
     */
    val allTasks: LiveData<List<TaskEntity>> =
        getTaskInfoFromDatabaseUseCase.getTaskFromDatabaseUseCase()

    /**
     * Метод вставки задачи в базу
     */
    fun insertTask(task: TaskEntity) = viewModelScope.launch(Dispatchers.IO) {
        insertTaskInfoFromDatabaseUseCase.insertTaskInDatabaseUseCase(task)
    }

    /**
     * Метод удаления задачи из базы
     */
    fun deleteTaskById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        deleteTaskInfoFromDatabaseUseCase.deleteTaskInDatabaseUseCase(id)
    }

    /**
     * Создание объекта задача
     */
    fun createTaskObj(nameTask: String): TaskEntity {
        val taskObj = TaskEntity(id = 0, nameTask = nameTask, checkTask = false)
        return taskObj
    }

    /**
     * Получение объекта заметка по Id
     */
    suspend fun getObjectTaskById(id: Int): TaskEntity? {
        return withContext(Dispatchers.IO) {
            getOneTaskInfoFromDatabaseUseCase.getOneTaskFromDatabaseUseCase(id = id)
        }
    }

    /**
     * Создание и присвоение значений задаче
     */
    fun createAndAssignTask(id: Int, callback: (TaskEntity) -> Unit) {
        viewModelScope.launch {
            val objectTask = getObjectTaskById(id)
            objectTask?.let {
                val task = createTaskObj(it.nameTask)
                callback(task)
            }
        }
    }

    /**
     * Обновление объекта заметка по Id
     */
    fun updateObjectTaskByIdUseCase(nameTask: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskInfoFromDatabaseUseCase.updateTaskInDatabaseUseCase(nameTask, id)
        }
    }

    /**
     * Обновление параметра выполнения задачи по Id
     */
    fun updateTaskCheckUseCase(checkTask: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            checkCompletionTaskUseCase.updateTaskCheckUseCase(checkTask, id)
        }
    }

    /**
     * Отправка уведомления по задаче
     */
    fun sendPushTask(context: Fragment, header: String, body: String) {
        UtilsApp.sendPushInfo(context, header, body)
    }
}