package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NotesAppSettingViewModel @Inject constructor() : ViewModel() {
    private val userNameLiveData = MutableLiveData<String>()
    val userName: LiveData<String> get() = userNameLiveData

    /**
     * Метод установки имени пользователя в настройках
     */
    fun setUserName(name: String) {
        userNameLiveData.value = name
    }
}