package com.bignerdranch.android.notesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class GetListNoteInfoFromDatabaseUseCase @Inject constructor(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка заметок
     */
    fun getNoteFromDatabaseUseCase(): LiveData<List<NoteEntity>> {
        return repositoryImpl.getNoteFromDatabase()
    }
}