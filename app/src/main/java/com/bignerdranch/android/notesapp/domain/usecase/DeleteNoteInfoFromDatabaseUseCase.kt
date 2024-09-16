package com.bignerdranch.android.notesapp.domain.usecase

import com.bignerdranch.android.notesapp.data.database.room_database.BaseNote
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl

class DeleteNoteInfoFromDatabaseUseCase(private val repositoryImpl: DatabaseRepositoryImpl) {

    /**
     *  Удаления заметки
     */
   suspend fun deleteNoteInDatabaseUseCase(nameNote: BaseNote)  {
       repositoryImpl.deleteNoteInDatabase(nameNote)
   }
}