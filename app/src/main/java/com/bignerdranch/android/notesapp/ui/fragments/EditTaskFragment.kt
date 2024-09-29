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
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.EditTaskFragmentBinding
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class EditTaskFragment : Fragment() {
    private lateinit var binding: EditTaskFragmentBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditTaskFragmentBinding.inflate(layoutInflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Ловим во фрагменте данные переданные по Bundle и присваиваем элементу фрагмента
         */
        val id = arguments?.getInt("Id")
        taskViewModel.createAndAssignTask(id ?: 0) { task ->
            val textBodyTask = task.nameTask
            binding.editBodyTaskET.setText(textBodyTask)
        }

        /**
         * Обработка кнопки [Сохранить]
         */
        binding.saveEditTaskBTN.setOnClickListener {
            taskViewModel.updateObjectTaskByIdUseCase(
                binding.editBodyTaskET.text.toString(),
                id!!
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    /**
     * Раздуваем созданное меню для удаления задачи
     */
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Удаление задачи за базы данных
     */
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                arguments?.getInt("Id")?.let { taskViewModel.deleteTaskById(it) }
                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}