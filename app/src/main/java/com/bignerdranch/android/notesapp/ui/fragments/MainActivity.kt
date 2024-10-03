package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bignerdranch.android.notesapp.MyApplication
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.storage.shared_preferences.PreferencesBase
import com.bignerdranch.android.notesapp.databinding.ActivityMainBinding
import com.bignerdranch.android.notesapp.ui.view_model.NotesAppSettingViewModel
import com.bignerdranch.android.notesapp.utils.UtilsApp
import javax.inject.Inject

@Suppress("DEPRECATION")
class MainActivity: AppCompatActivity() {
    private lateinit var configuration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var bindingActivityMainBinding: ActivityMainBinding
    @Inject
    lateinit var userViewModel: NotesAppSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        bindingActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivityMainBinding.root)
        setSupportActionBar(bindingActivityMainBinding.contentMain.appBar.toolbar2)
        UtilsApp.createNotificationChannel(this)
        navController = findNavController(R.id.fragmentContainerView)

        //Регулируем отображение BottomNavigationView для фрагментов
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainNotesFragment -> bindingActivityMainBinding.contentMain.bottomNav.visibility =
                    View.VISIBLE

                R.id.mainTasksFragment -> bindingActivityMainBinding.contentMain.bottomNav.visibility =
                    View.VISIBLE

                else -> {
                    bindingActivityMainBinding.contentMain.bottomNav.visibility =
                        View.GONE
                }
            }
        }

        //Отключаем свап вызова меню из фрагмента
        bindingActivityMainBinding.drawerLayoutViewId.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        //Настройка и отображение меню в ToolBar
        configuration = AppBarConfiguration(
            setOf(
                R.id.mainNotesFragment,
                R.id.mainTasksFragment
            ), bindingActivityMainBinding.drawerLayoutViewId
        )
        setupActionBarWithNavController(navController, configuration)
        bindingActivityMainBinding.navView.setupWithNavController(navController)

        //Обработка нажатий на элементы BottomNavigation
        bindingActivityMainBinding.contentMain.bottomNav.setOnItemSelectedListener { item ->
            onBottomNavItemSelected(item)
        }

        // Наблюдение за изменениями имени пользователя
        userViewModel.userName.observe(this, Observer { name ->
            updateUI(name)
        })

        // Установка начального имени пользователя
        PreferencesBase.getUserName(this)?.let { userViewModel.setUserName(it) }
    }

    /**
     * Обработка возврата между фрагментами
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(configuration) || super.onSupportNavigateUp()
    }

    /**
     * Функция обработки нажатий на элементы BottomNavigation
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

    /**
     * Обновляем UI для отрисовки имени пользователя
     */
    private fun updateUI(name: String) {
        val userNameTextView = bindingActivityMainBinding.navView.getHeaderView(0)
            .findViewById<TextView>(R.id.textUserName)
        userNameTextView.text = name
    }
}