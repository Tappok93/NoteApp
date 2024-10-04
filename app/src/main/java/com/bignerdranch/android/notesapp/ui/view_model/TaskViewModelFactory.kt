package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.domain.usecase.task.CheckCompletionTaskUseCase
import com.bignerdranch.android.notesapp.domain.usecase.task.DeleteTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.task.GetListTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.task.GetOneTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.task.InsertTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.task.UpdateTaskInfoFromDatabaseUseCase
import javax.inject.Inject

class TaskViewModelFactory @Inject constructor(
    private val deleteTaskInfoFromDatabaseUseCase: DeleteTaskInfoFromDatabaseUseCase,
    private val getTaskInfoFromDatabaseUseCase: GetListTaskInfoFromDatabaseUseCase,
    private val insertTaskInfoFromDatabaseUseCase: InsertTaskInfoFromDatabaseUseCase,
    private val getOneTaskInfoFromDatabaseUseCase: GetOneTaskInfoFromDatabaseUseCase,
    private val updateTaskInfoFromDatabaseUseCase: UpdateTaskInfoFromDatabaseUseCase,
    private val checkCompletionTaskUseCase: CheckCompletionTaskUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(
                deleteTaskInfoFromDatabaseUseCase,
                getTaskInfoFromDatabaseUseCase,
                insertTaskInfoFromDatabaseUseCase,
                getOneTaskInfoFromDatabaseUseCase,
                updateTaskInfoFromDatabaseUseCase,
                checkCompletionTaskUseCase
            ) as T
        }
        throw IllegalArgumentException("Неизветстный класс ViewModel")
    }
}