package com.bignerdranch.android.notesapp.data.storage.shared_preferences

import android.content.Context


class PreferencesBase {

    companion object {

        private const val NAME_PREFERENCE = "NotesAppPreference"

        /**
         * Метод сохранеиния в SharedPreference
         */
        fun saveUserName(context: Context, text: String) {
            val sharedPreferences =
                context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("saved_name", text)
                apply()
            }
        }

        /**
         * Метод получения из SharedPreference
         */
        fun getUserName(context: Context): String? {
            val sharedPreferences =
                context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
            return sharedPreferences.getString("saved_name", "")
        }
    }

}