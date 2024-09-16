package com.bignerdranch.android.notesapp.ui.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.shared_preferences.PreferencesBase
import com.bignerdranch.android.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var configuration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var bindingActivityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivityMainBinding.root)
        setSupportActionBar(bindingActivityMainBinding.contentMain.appBar.toolbar2)
        navController = findNavController(R.id.fragmentContainerView)

        // Отключаем свап вызова меню из фрагмента
        bindingActivityMainBinding.drawerLayoutViewId.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        /**
         * Настройка и отображение меню в ToolBar
         */
        configuration = AppBarConfiguration(
            setOf(
                R.id.mainNotesFragment,
                R.id.mainTasksFragment
            ), bindingActivityMainBinding.drawerLayoutViewId
        )
        setupActionBarWithNavController(navController, configuration)
        bindingActivityMainBinding.navView.setupWithNavController(navController)

        /**
         * Обработка нажатий на элементы BootomNavigation
         */
        bindingActivityMainBinding.contentMain.bottomNav.setOnItemSelectedListener { item ->
            onBottomNavItemSelected(item)
        }
    }

    /**
     * Обработка возврата между фрагментами
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(configuration) || super.onSupportNavigateUp()
    }

    /**
     * Функция обработки нажатий на элементы BootomNavigation
     */
    private fun onBottomNavItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.noteId -> {
                navController.navigate(R.id.mainNotesFragment)
                true
            }

            R.id.taskId -> {
                navController.navigate(R.id.mainTasksFragment)
                true
            }

            else -> false
        }
    }

}