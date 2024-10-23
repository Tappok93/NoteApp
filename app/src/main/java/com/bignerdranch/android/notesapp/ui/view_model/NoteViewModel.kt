package com.bignerdranch.android.notesapp.ui.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.domain.usecase.note.DeleteNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.note.GetListNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.note.GetOneNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.note.InsertNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.note.UpdateNoteInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.utils.UtilsApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val deleteNoteInfoFromDatabaseUseCase: DeleteNoteInfoFromDatabaseUseCase,
    getNoteInfoFromDatabaseUseCase: GetListNoteInfoFromDatabaseUseCase,
    private val insertNoteInfoFromDatabaseUseCase: InsertNoteInfoFromDatabaseUseCase,
    private val getOneNoteInfoFromDatabaseUseCase: GetOneNoteInfoFromDatabaseUseCase,
    private val updateNoteInfoFromDatabaseUseCase: UpdateNoteInfoFromDatabaseUseCase
) : ViewModel() {

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
        return NoteEntity(id = 0, nameNote = nameNote, nameHeaderNote = nameHeaderNote, date = date)
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
        viewModelScope.launch(Dispatchers.IO) {
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


