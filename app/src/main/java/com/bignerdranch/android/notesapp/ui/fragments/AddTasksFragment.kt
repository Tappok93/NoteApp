package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddNotesBinding
import com.bignerdranch.android.notesapp.databinding.FragmentAddTasksBinding
import com.bignerdranch.android.notesapp.databinding.FragmentMainTasksBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import com.bignerdranch.android.notesapp.utils.UtilsApp
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Date


class AddTasksFragment : Fragment() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentAddTasksBinding
    private val bottomNavView = activity?.
    findViewById<BottomNavigationView>(R.id.bottomNav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTasksBinding.inflate(layoutInflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        UtilsApp.createNotificationChannel(requireContext())
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Скрываем BottomNavigationView
        val bottomNavView = activity?.
        findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.GONE)
    }

    override fun onDestroy() {
        super.onDestroy()

        //Отображаем BottomNavigationView
        val bottomNavView = activity?.
        findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.VISIBLE)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                val textTask = binding.textTask.text.toString()
                val newTask = taskViewModel.createTaskObj(textTask)

                taskViewModel.insertTask(newTask)
                UtilsApp.sendPushInfo(
                    requireContext(),
                    R.string.TextHeaderTask.toString(),
                    R.string.TextBodyTask.toString()
                )
                binding.textTask.text.clear()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}