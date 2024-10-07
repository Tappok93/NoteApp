package com.bignerdranch.android.notesapp.di

import com.bignerdranch.android.notesapp.ui.fragments.MainActivity
import com.bignerdranch.android.notesapp.ui.fragments.note.MainNoteFragment
import com.bignerdranch.android.notesapp.ui.fragments.task.MainTaskFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [UseCaseModule::class, AppModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {
    /**
     * Инекции для Activity\Fragment
     */
    fun inject(mainActivity: MainActivity)
    fun inject(mainNoteFragment: MainNoteFragment)
    fun inject(mainTaskFragment: MainTaskFragment)
}