package com.bignerdranch.android.notesapp.di

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.usecase.CheckCompletionTaskUseCase
import com.bignerdranch.android.notesapp.domain.usecase.DeleteTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetListTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.GetOneTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.InsertTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.domain.usecase.UpdateTaskInfoFromDatabaseUseCase
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideDatabaseRepositoryImpl(): DatabaseRepositoryImpl {
        return DatabaseRepositoryImpl()
    }

}