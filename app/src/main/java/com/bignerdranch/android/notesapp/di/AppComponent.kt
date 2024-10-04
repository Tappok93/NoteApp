package com.bignerdranch.android.notesapp.di

import android.app.Application
import android.content.Context
import com.bignerdranch.android.notesapp.data.repository.DatabaseRepositoryImpl
import com.bignerdranch.android.notesapp.domain.interfaceDatabaseRepositiry.DatabaseRepository
import com.bignerdranch.android.notesapp.ui.fragments.MainActivity
import com.bignerdranch.android.notesapp.ui.fragments.note.MainNoteFragment
import com.bignerdranch.android.notesapp.ui.fragments.task.MainTaskFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [UseCaseModule::class, AppModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainNoteFragment: MainNoteFragment)
    fun inject(mainTaskFragment: MainTaskFragment)

    fun getDatabaseRepository(): DatabaseRepository
    fun getContext(): Context
}