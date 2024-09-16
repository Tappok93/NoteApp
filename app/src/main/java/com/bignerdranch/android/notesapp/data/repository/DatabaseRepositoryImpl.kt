package com.bignerdranch.android.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.AppDatabase
import com.bignerdranch.android.notesapp.data.database.room_database.BaseDao
import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.database.room_database.BaseTask
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import com.bignerdranch.android.notesapp.ui.context.MyApplication

class DatabaseRepositoryImpl : DatabaseRepository {

    private val baseDao: BaseDao
    val context = MyApplication.getAppContext()

    init {
        val database = AppDatabase.getInstance(context)
        baseDao = database!!.getBaseDao()

    }

    /**
     * Вставка информации по заметке в базу данных
     */
    override suspend fun insertNoteInDatabase(infoNote: BaseNote) {
        baseDao.insertInfoNoteInDatabase(infoNote)
    }

    /**
     * Вставка информации по задаче в базу данных
     */
    override suspend fun insertTaskInDatabase(infoTask: BaseTask) {
        baseDao.insertInfoTaskInDatabase(infoTask)
    }

    /**
     * Удаление информации по заметке из базы данных
     */
    override suspend fun deleteNoteInDatabase(nameNote: BaseNote) {
        baseDao.deleteInfoBaseNote(nameNote)
    }

    /**
     * Удаление информации по задаче из базы данных
     */
    override suspend fun deleteTaskInDatabase(nameTask: BaseTask) {
        baseDao.deleteInfoBaseTask(nameTask)
    }

    /**
     * Получение списка заметок из базы данных
     */
    override fun getNoteFromDatabase(): LiveData<List<BaseNote>> {
        return baseDao.getInfoNote()
    }

    /**
     * Получение списка задач из базы данных
     */
    override fun getTaskFromDatabase(): LiveData<List<BaseTask>> {
        return baseDao.getInfoTask()
    }
}