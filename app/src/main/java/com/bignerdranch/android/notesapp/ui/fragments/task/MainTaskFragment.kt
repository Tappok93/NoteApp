package com.bignerdranch.android.notesapp.ui.fragments.task

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notesapp.MyApplication
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.databinding.FragmentMainTasksBinding
import com.bignerdranch.android.notesapp.ui.adapter.TaskRecyclerViewAdapter
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


@Suppress("DEPRECATION")
class MainTaskFragment : Fragment(), TaskRecyclerViewAdapter.InfoTaskItemClickListener {
    private lateinit var binding: FragmentMainTasksBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TaskRecyclerViewAdapter
    private val bundle = Bundle()

    @Inject
    lateinit var taskViewModel: TaskViewModel

    /**
     * Подключаем Dagger2 к фрагменту
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainTasksBinding.inflate(layoutInflater, container, false)
        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

        adapter = TaskRecyclerViewAdapter(emptyList())
        recycler.adapter = adapter
        adapter.setInfoListener(this)

        taskViewModel.allTasks.observe(
            viewLifecycleOwner
        ) { dataList ->
            dataList?.let {
                adapter.updateTaskListFromDB(it)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(recycler)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Нажатие кнопки [Добавить задачу]
         */
        binding.addTaskBTN.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .build()
            findNavController().navigate(
                R.id.action_mainTasksFragment_to_addTasksFragment,
                null,
                navOptions
            )
        }

        /**
         * Поиск задач по SearchView
         */
        binding.searchTaskSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filterTask(newText)
                return true
            }
        })
    }

    /**
     * Передача данных и переход на другой фрагмент по Bundle
     */
    override fun onItemClickListener(taskEntity: TaskEntity) {
        bundle.putInt("Id", taskEntity.id)
        findNavController().navigate(R.id.action_mainTasksFragment_to_editTaskFragment, bundle)
    }

    /**
     * Обработка нажатий на radioButtom, сохранение состояния в базу данных
     */
    override fun editElementClickListener(taskEntity: TaskEntity) {
        taskViewModel.updateTaskCheckUseCase(
            taskEntity.checkTask,
            taskEntity.id
        )
    }

    // Реализация ItemTouchHelper для свайпа
    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val taskToRemove = adapter.getTaskAtPosition(position)

            // Удалите задачу из базы данных
            taskViewModel.deleteTaskById(taskToRemove.id)

            // Удалите элемент из адаптера
            adapter.removeTaskAtPosition(position)

            // Реализация Snackbar с возможностью отмены
            val myBar = Snackbar.make(binding.root, "Задача удалена", Snackbar.LENGTH_LONG)
            myBar.setAction("Отменить") {
                taskViewModel.insertTask(taskToRemove)
                adapter.addTaskAtPosition(position, taskToRemove)
            }
            myBar.show()
        }
    }
}