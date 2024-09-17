package com.bignerdranch.android.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.AppDatabase
import com.bignerdranch.android.notesapp.data.database.room_database.BaseDao
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import com.bignerdranch.android.notesapp.MyApplication

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
    override suspend fun insertNoteInDatabase(infoNote: NoteEntity) {
        baseDao.insertInfoNoteInDatabase(infoNote)
    }

    /**
     * Вставка информации по задаче в базу данных
     */
    override suspend fun insertTaskInDatabase(infoTask: TaskEntity) {
        baseDao.insertInfoTaskInDatabase(infoTask)
    }

    /**
     * Удаление информации по заметке из базы данных
     */
    override suspend fun deleteNoteInDatabase(nameNote: NoteEntity) {
        baseDao.deleteInfoBaseNote(nameNote)
    }

    /**
     * Удаление информации по задаче из базы данных
     */
    override suspend fun deleteTaskInDatabase(nameTask: TaskEntity) {
        baseDao.deleteInfoBaseTask(nameTask)
    }

    /**
     * Получение списка заметок из базы данных
     */
    override fun getNoteFromDatabase(): LiveData<List<NoteEntity>> {
        return baseDao.getInfoNote()
    }

    /**
     * Получение списка задач из базы данных
     */
    override fun getTaskFromDatabase(): LiveData<List<TaskEntity>> {
        return baseDao.getInfoTask()
    }
}