package com.bignerdranch.android.notesapp.data.database.room_database

import androidx.lifecycle.LiveData
import androidx.room.Dao
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
    @Query("DELETE FROM TableTask WHERE id = :id")
    suspend fun deleteTaskByName(id: Int)

    @Query("DELETE FROM TableNote WHERE id = :id")
    suspend fun deleteNoteByName(id: Int)

    /**
     * Методы получения заметки\задачи по id из списка базы данных
     */
    @Query("SELECT * FROM TableNote WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Query("SELECT * FROM TableTask WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?

    /**
     * Методы редактирования заметки\задачи по id в базе данных
     */
    @Query("UPDATE TableNote SET nameNote = :nameNote WHERE id = :id")
    suspend fun updateNote(nameNote: String, id: Int)

    @Query("UPDATE TableTask SET nameTask = :nameTask WHERE id = :id")
    suspend fun updateTask(nameTask: String, id: Int)

    /**
     * Метод обновления параметра проверки у задачи по id в базе данных
     */
    @Query("UPDATE TableTask SET checkTask = :checkTask WHERE id = :id")
    suspend fun updateTaskCheck(checkTask: Boolean, id: Int)
}