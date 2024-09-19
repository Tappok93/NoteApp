package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetListNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetOneNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertNoteInfoFromDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteViewModel : ViewModel() {
    private val repositoryDatabase = DatabaseRepositoryImpl()
    private val deleteNoteInfoFromDatabaseUseCase =
        DeleteNoteInfoFromDatabaseUseCase(repositoryDatabase)
    private val getNoteInfoFromDatabaseUseCase =
        GetListNoteInfoFromDatabaseUseCase(repositoryDatabase)
    private val insertNoteInfoFromDatabaseUseCase =
        InsertNoteInfoFromDatabaseUseCase(repositoryDatabase)
    private val getOneNoteInfoFromDatabaseUseCase =
        GetOneNoteInfoFromDatabaseUseCase(repositoryDatabase)

    /**
     * Получение списка заметок из базы
     */
    val allNotes: LiveData<List<NoteEntity>> =
        getNoteInfoFromDatabaseUseCase.getNoteFromDatabaseUseCase()

    /**
     * Метод вставки заметки в базу
     */
    suspend fun insertNote(infoNote: NoteEntity) = withContext(Dispatchers.IO) {
        insertNoteInfoFromDatabaseUseCase.insertNoteInDatabaseUseCase(infoNote)
    }

    /**
     * Создание объекта заметка
     */
    fun createNoteObj(nameNote: String, nameHeaderNote: String, date: String): NoteEntity {
        val noteObj =
            NoteEntity(id = 0, nameNote = nameNote, nameHeaderNote = nameHeaderNote, date = date)
        return noteObj
    }

    /**
     * Получение объекта заметка по Id
     */
    suspend fun getObjectNoteById(id: Int): NoteEntity? {
        return getOneNoteInfoFromDatabaseUseCase.getOneNoteFromDatabaseUseCase(id = id)
    }

    /**
     * Удаление объекта заметка по Id
     */
    suspend fun deleteObjectNoteByIdUseCase(id: Int) {
        withContext(Dispatchers.IO) {
            deleteNoteInfoFromDatabaseUseCase.deleteNoteInDatabaseUseCase(id)
        }
    }
}


