package com.bignerdranch.android.notesapp.domain

interface DatabaseRepository {
    fun insertNoteInDatabase()
    fun insertTaskInDatabase()
    fun deleteNoteInDatabase()
    fun deleteTaskInDatabase()
    fun getNoteFromDatabase()
    fun getTaskFromDatabase()
}