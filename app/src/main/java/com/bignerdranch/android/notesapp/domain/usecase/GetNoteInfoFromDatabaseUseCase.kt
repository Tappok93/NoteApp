package com.bignerdranch.android.notesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class GetNoteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     * Получение списка заметок
     */
     fun getNoteFromDatabaseUseCase(): LiveData<List<BaseNote>> {
        return repositoryImpl.getNoteFromDatabase()
    }
}