package com.bignerdranch.android.notesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class GetListNoteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка заметок
     */
     fun getNoteFromDatabaseUseCase(): LiveData<List<NoteEntity>> {
        return repositoryImpl.getNoteFromDatabase()
    }
}