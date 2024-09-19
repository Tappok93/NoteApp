package com.bignerdranch.android.notesapp.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private val userNameLiveData = MutableLiveData<String>()
    val userName: LiveData<String> get() = userNameLiveData

    fun setUserName(name: String) {
        userNameLiveData.value = name
    }
}