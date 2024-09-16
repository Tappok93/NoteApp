package com.bignerdranch.android.notesapp.ui.view_model

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertNoteInfoFromDatabaseUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

class NoteViewModel : ViewModel() {
    private val repositoryDatabase = DatabaseRepositoryImpl()
    private var deleteNoteInfoFromDatabaseUseCase =
        DeleteNoteInfoFromDatabaseUseCase(repositoryDatabase)
    private var getNoteInfoFromDatabaseUseCase = GetNoteInfoFromDatabaseUseCase(repositoryDatabase)
    private var insertNoteInfoFromDatabaseUseCase =
        InsertNoteInfoFromDatabaseUseCase(repositoryDatabase)

    /**
     * Получение списка заметок из базы
     */
    val allNotes: LiveData<List<BaseNote>> =
        getNoteInfoFromDatabaseUseCase.getNoteFromDatabaseUseCase()

    /**
     * Метод вставки заметки в базу
     */
    fun insertNote(infoNote: BaseNote) = viewModelScope.launch {
        insertNoteInfoFromDatabaseUseCase.insertNoteInDatabaseUseCase(infoNote)
    }

    /**
     * Метод удаления заметки из базы
     */
    fun deleteNoteByName(nameNote: BaseNote) = viewModelScope.launch {
        deleteNoteInfoFromDatabaseUseCase.deleteNoteInDatabaseUseCase(nameNote)
    }

    /**
     * Создание объекта заметка
     */
    fun createNoteObj(nameNote: String, date: Date): BaseNote {
        val noteObj = BaseNote(id = 0, nameNote = nameNote, date = date)
        return noteObj
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
            .setSmallIcon(R.drawable.notes_icon_btn)
            .setContentTitle("Уведомление о заметке")
            .setContentText("Заметка сохранена.")
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


