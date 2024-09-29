package com.bignerdranch.android.notesapp.ui.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.DeleteNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetListNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetOneNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.UpdateNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.utils.UtilsApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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
    private val updateNoteInfoFromDatabaseUseCase =
        UpdateNoteInfoFromDatabaseUseCase(repositoryDatabase)

    lateinit var res: Job

    /**
     * Получение списка заметок из базы
     */
    val allNotes: LiveData<List<NoteEntity>> =
        getNoteInfoFromDatabaseUseCase.getNoteFromDatabaseUseCase()

    /**
     * Метод вставки заметки в базу
     */
    fun insertNote(infoNote: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
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
     private suspend fun getObjectNoteById(id: Int): NoteEntity? {
        return withContext(Dispatchers.IO) {
            getOneNoteInfoFromDatabaseUseCase.getOneNoteFromDatabaseUseCase(
                id = id
            )
        }
    }

    /**
     * Создание и присвоение значений заметке
     */
    fun createAndAssignNote(id: Int, callback: (NoteEntity) -> Unit) {
        viewModelScope.launch {
            val objectNote = getObjectNoteById(id)
            objectNote?.let {
                val note = createNoteObj(it.nameNote, it.nameHeaderNote, it.date)
                callback(note)
            }
        }
    }

    /**
     * Удаление объекта заметка по Id
     */
    fun deleteObjectNoteByIdUseCase(id: Int) {
        res = viewModelScope.launch(Dispatchers.IO) {
            deleteNoteInfoFromDatabaseUseCase.deleteNoteInDatabaseUseCase(id)
        }
    }

    /**
     * Обновление объекта заметка по Id
     */
    fun updateObjectNoteByIdUseCase(nameNote: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteInfoFromDatabaseUseCase.updateNoteInDatabaseUseCase(nameNote, id)
        }
    }

    /**
     * Отправка уведомления по заметке
     */
    fun sendPushNote(context: Fragment, header: String, body: String) {
        UtilsApp.sendPushInfo(context, header, body)
    }
}


