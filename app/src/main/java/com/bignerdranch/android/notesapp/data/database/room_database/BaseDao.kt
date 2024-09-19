package com.bignerdranch.android.notesapp.data.database.room_database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity

@Dao
interface BaseDao {

    /**
     * Методы добавления информаций по заметке\задаче в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfoNoteInDatabase(infoNote: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfoTaskInDatabase(infoTask: TaskEntity)

    /**
     * Методы получения списка заметок\задач из базы данных
     */
    @Query("SELECT * FROM TableNote")
    fun getInfoNote(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM TableTask")
    fun getInfoTask(): LiveData<List<TaskEntity>>

    /**
     * Методы удаления заметок\задач из списка базы данных
     */
    @Delete
    suspend fun deleteInfoBaseNote(nameNote: NoteEntity)

    @Delete
    suspend fun deleteInfoBaseTask(nameTask: TaskEntity)

    /**
     * Метод удаления заметки по наиминованию из списка базы данных
     */
//    @Query("DELETE FROM TableNote WHERE nameNote = :nameNote")
//    suspend fun deleteNoteByName(nameNote: String)

}