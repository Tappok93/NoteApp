package com.bignerdranch.android.notesapp.data.database.shared_preferences

import android.content.Context

class PreferencesBase {

    companion object {
        /**
         * Метод сохранеиния в SharedPreference
         */
        fun saveUserName(context: Context, text: String) {
            val sharedPreferences = context.getSharedPreferences("NameUser", Context.MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("saved_name", text)
                apply()
            }
        }

        /**
         * Метод получения из SharedPreference
         */
        fun getUserName(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences("NameUser", Context.MODE_PRIVATE)
            return sharedPreferences.getString("saved_name", "")
        }
    }

}