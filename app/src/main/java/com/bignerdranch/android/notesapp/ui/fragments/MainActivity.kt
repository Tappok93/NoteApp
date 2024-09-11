package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.ActivityMainBinding
import com.bignerdranch.android.notesapp.databinding.ContentMainBinding
import com.bignerdranch.android.notesapp.ui.view_model.SharedViewModel
import com.google.android.material.navigation.NavigationView

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

        // Слушатель для обновления Tollbar для активного фрагмента
        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateToolbar(destination.id)
            invalidateOptionsMenu()
        }

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

        /**
         * Обработка нажатий на элемент NavigationDrawer
         */
        bindingActivityMainBinding.navView.setNavigationItemSelectedListener { menuItem ->
            onNavigationItemSelected(menuItem)

            // Закрытие меню после нажатия на элеменет меню
            bindingActivityMainBinding.drawerLayoutViewId.closeDrawer(GravityCompat.START)
            true
        }

    }

    /**
     * Обработка возврата между фрагментами
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(configuration) || super.onSupportNavigateUp()
    }

    /**
     * Функция обновления меню Toolbar для каждого фрагмента
     */
    private fun updateToolbar(destinationId: Int) {
        val toolbar = bindingActivityMainBinding.contentMain.appBar.toolbar2
             toolbar.menu.clear()

        when (destinationId) {
            R.id.addNotesFragment,
            R.id.settingFragment,
            R.id.addTasksFragment -> {
                toolbar.inflateMenu(R.menu.save_bottom_menu)
            }
        }
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

    /**
     * Функция обработки нажатий на элемент NavigationDrawer
     */
    private fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        val currentFragmentId = navController.currentDestination?.id

         when (menuItem.itemId) {
            R.id.nav_item_one -> {
                if (currentFragmentId == R.id.mainNotesFragment) {
                    navController.navigate(R.id.action_mainNotesFragment_to_settingFragment)
                } else if (currentFragmentId == R.id.mainTasksFragment) {
                    navController.navigate(R.id.action_mainTasksFragment_to_settingFragment)
                }
            }
        }
       return true
    }
}