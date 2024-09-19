package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.databinding.FragmentMainTasksBinding
import com.bignerdranch.android.notesapp.ui.adapter.TaskRecyclerViewAdapter
import com.bignerdranch.android.notesapp.ui.view_model.TaskViewModel


class MainTaskFragment : Fragment(), TaskRecyclerViewAdapter.InfoTaskItemClickListener {
    private lateinit var binding: FragmentMainTasksBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TaskRecyclerViewAdapter
    private lateinit var taskViewModel: TaskViewModel
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainTasksBinding.inflate(layoutInflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

        adapter = TaskRecyclerViewAdapter(emptyList())
        recycler.adapter = adapter
        adapter.setInfoListener(this)

        taskViewModel.allTasks.observe(
            viewLifecycleOwner
        ) { dataList ->
            dataList?.let {
                adapter.updateList(it)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Нажатие кнопки [Добавить задачу]
         */
        binding.addTaskBTN.setOnClickListener {
            findNavController().navigate(R.id.action_mainTasksFragment_to_addTasksFragment)
        }
    }

    /**
     * Передача данных и переход на другой фрагмент по Bundle
     */
    override fun onItemClickListener(taskEntity: TaskEntity) {
        bundle.putString("nameBodyTask", taskEntity.nameTask)
        findNavController().navigate(R.id.action_mainTasksFragment_to_editTaskFragment, bundle)
    }

}