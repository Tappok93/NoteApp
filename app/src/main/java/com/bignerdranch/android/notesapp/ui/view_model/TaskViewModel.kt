package com.bignerdranch.android.notesapp.ui.view_model

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.database.room_database.BaseTask
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.DeleteTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertTaskInfoFromDatabaseUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel: ViewModel() {
    private val repositoryDatabase = DatabaseRepositoryImpl()
    private val deleteTaskInfoFromDatabaseUseCase =  DeleteTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private var getTaskInfoFromDatabaseUseCase = GetTaskInfoFromDatabaseUseCase(repositoryDatabase)
    private var insertTaskInfoFromDatabaseUseCase = InsertTaskInfoFromDatabaseUseCase(repositoryDatabase)

    /**
     * Получение списка задач из базы данных
     */
    val allTasks: LiveData<List<BaseTask>> = getTaskInfoFromDatabaseUseCase.getTaskFromDatabaseUseCase()

    /**
     * Метод вставки задачи в базу
     */
    fun insertTask(task: BaseTask) = viewModelScope.launch {
        insertTaskInfoFromDatabaseUseCase.insertTaskInDatabaseUseCase(task)
    }

    /**
     * Метод удаления задачи из базы
     */
    fun deleteTaskByName(nameTask: BaseTask) = viewModelScope.launch {
        deleteTaskInfoFromDatabaseUseCase.deleteTaskInDatabaseUseCase(nameTask)
    }

    /**
     * Создание объекта задача
     */
    fun createTaskObj(nameTask: String): BaseTask {
        val taskObj = BaseTask(id = 0, nameTask = nameTask)
        return taskObj
    }

    /**
     * Создаём канал для Push уведомлений
     */
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel"
            val channelName = "My Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Канал уведомлений"
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Создаём Push уведомление
     */
    fun sendPushInfo(context: Context) {
        val builder = NotificationCompat.Builder(context, "my_channel_id")
            .setSmallIcon(R.drawable.task_icon_btn)
            .setContentTitle("Уведомление о задаче")
            .setContentText("Задача сохранена.")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Высокий приоритет
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }
    }
}