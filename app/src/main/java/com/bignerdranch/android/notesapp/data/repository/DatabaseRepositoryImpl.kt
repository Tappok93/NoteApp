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
     * Удаление заметки по ID
     */
    override suspend fun deleteByIdNoteInDatabase(id: Int) {
        baseDao.deleteNoteByName(id)
    }

    /**
     * Удаление задачи по ID
     */
    override suspend fun deleteByIdTaskInDatabase(id: Int) {
        baseDao.deleteTaskByName(id)
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

    /**
     * Получение объекта заметка по id
     */
    override suspend fun getNoteById(id: Int): NoteEntity? {
        return baseDao.getNoteById(id)
    }

    /**
     * Получение объекта задача по id
     */
    override suspend fun getTaskById(id: Int): TaskEntity? {
        return baseDao.getTaskById(id)
    }
}