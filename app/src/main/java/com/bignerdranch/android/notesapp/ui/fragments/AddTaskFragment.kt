package com.bignerdranch.android.notesapp.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddTasksBinding
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import com.bignerdranch.android.notesapp.utils.UtilsApp
import com.google.android.material.bottomnavigation.BottomNavigationView


@Suppress("DEPRECATION")
class AddTaskFragment : Fragment() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentAddTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTasksBinding.inflate(layoutInflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Скрываем BottomNavigationView
        val bottomNavView = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.GONE)
    }

    override fun onDestroy() {
        super.onDestroy()

        //Отображаем BottomNavigationView
        val bottomNavView = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.VISIBLE)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {

                if (context?.let {
                        ContextCompat.checkSelfPermission(
                            it,
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    // Разрешение на отправку уведомлений не предоставлено, показываем диалоговое окно с запросом на предоставление разрешения
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                    return true
                }

                val textTask = binding.textTask.text.toString()
                val newTask = taskViewModel.createTaskObj(textTask)

                taskViewModel.insertTask(newTask)
                UtilsApp.sendPushInfo(
                    requireContext(),
                    getString(R.string.TextHeaderTask),
                    getString(R.string.TextBodyTask)
                )
                binding.textTask.text.clear()

                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}