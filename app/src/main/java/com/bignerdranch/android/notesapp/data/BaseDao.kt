package com.bignerdranch.android.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BaseDao {

    /**
     * Методы добавления информаций по заметке\задаче в базу данных
     */
    @Insert
    fun insertInfoNoteInDatabase(info: BaseNote)
    @Insert
    fun insertInfoTaskInDatabase(info: BaseTask)

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
    @Query("DELETE FROM TableNote WHERE nameNote = :nameNote")
    fun deleteInfoBaseNote(nameNote: String)

    @Query("DELETE FROM TableTask WHERE nameTask = :nameTask")
    fun deleteInfoBaseTask(nameTask: String)
}