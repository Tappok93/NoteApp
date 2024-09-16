package com.bignerdranch.android.notesapp.data.database.room_database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BaseDao {

    /**
     * Методы добавления информаций по заметке\задаче в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfoNoteInDatabase(infoNote: BaseNote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfoTaskInDatabase(infoTask: BaseTask)

    /**
     * Методы получения списка заметок\задач из базы данных
     */
    @Query("SELECT * FROM TableNote")
    fun getInfoNote(): LiveData<List<BaseNote>>

    @Query("SELECT * FROM TableTask")
    fun getInfoTask(): LiveData<List<BaseTask>>

    /**
     * Методы удаления заметок\задач из списка базы данных
     */
    @Delete
    suspend fun deleteInfoBaseNote(nameNote: BaseNote)
    @Delete
    suspend fun deleteInfoBaseTask(nameTask: BaseTask)

}