package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertNoteInfoFromDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
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
    val allNotes: LiveData<List<NoteEntity>> =
        getNoteInfoFromDatabaseUseCase.getNoteFromDatabaseUseCase()

    /**
     * Метод вставки заметки в базу
     */
    fun insertNote(infoNote: NoteEntity) = viewModelScope.launch {
        insertNoteInfoFromDatabaseUseCase.insertNoteInDatabaseUseCase(infoNote)
    }

    /**
     * Метод удаления заметки из базы
     */
    fun deleteNoteByName(nameNote: NoteEntity) = viewModelScope.launch {
        deleteNoteInfoFromDatabaseUseCase.deleteNoteInDatabaseUseCase(nameNote)
    }

    /**
     * Создание объекта заметка
     */
    fun createNoteObj(nameNote: String, nameHeaderNote: String, date: String): NoteEntity {
        val noteObj =
            NoteEntity(id = 0, nameNote = nameNote, nameHeaderNote = nameHeaderNote, date = date)
        return noteObj
    }
}


