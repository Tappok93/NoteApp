package com.bignerdranch.android.notesapp.ui.fragments.task

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.MyApplication
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddTasksBinding
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import javax.inject.Inject

@Suppress("DEPRECATION")
class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTasksBinding

    @Inject
    lateinit var taskViewModel: TaskViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTasksBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Раздуваем созданное меню
     */
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

                //Вставка задачи в БД
                taskViewModel.insertTask(taskViewModel.createTaskObj(binding.textTask.text.toString()))

                //Отправка уведомления
                taskViewModel.sendPushTask(
                    this,
                    getString(R.string.TextHeaderTask),
                    getString(R.string.TextBodyTask)
                )

                //Очищаем поле ввода
                binding.textTask.text.clear()

                //Переход на предыдущий фрагмент стека
                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}