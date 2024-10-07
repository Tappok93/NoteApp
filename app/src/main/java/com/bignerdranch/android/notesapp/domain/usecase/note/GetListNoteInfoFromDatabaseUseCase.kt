package com.bignerdranch.android.notesapp.domain.usecase.note

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import javax.inject.Inject

class GetListNoteInfoFromDatabaseUseCase @Inject constructor(private val repository: DatabaseRepository) {

    /**
     * Получение списка заметок
     */
    fun getNoteFromDatabaseUseCase(): LiveData<List<NoteEntity>> {
        return repository.getNoteFromDatabase()
    }
}