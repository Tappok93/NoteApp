package com.bignerdranch.android.notesapp.di

import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import com.bignerdranch.android.notesapp.ui.fragments.MainActivity
import com.bignerdranch.android.notesapp.ui.fragments.MainNoteFragment
import com.bignerdranch.android.notesapp.ui.fragments.MainTaskFragment
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [UseCaseModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainNoteFragment: MainNoteFragment)
    fun inject(mainTaskFragment: MainTaskFragment)

    fun getDatabaseRepository(): DatabaseRepositoryImpl
}