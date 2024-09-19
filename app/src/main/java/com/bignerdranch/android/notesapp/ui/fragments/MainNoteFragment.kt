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
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.databinding.FragmentMainNotesBinding
import com.bignerdranch.android.notesapp.ui.adapter.NoteRecyclerViewAdapter
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel


class MainNoteFragment : Fragment(), NoteRecyclerViewAdapter.InfoNoteItemClickListener {
    private lateinit var binding: FragmentMainNotesBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NoteRecyclerViewAdapter
    private lateinit var noteViewModel: NoteViewModel
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNotesBinding.inflate(layoutInflater, container, false)
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

        adapter = NoteRecyclerViewAdapter(emptyList())
        recycler.adapter = adapter
        adapter.setInfoListener(this)

        noteViewModel.allNotes.observe(
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
         * Нажатие кнопки [Добавить заметку]
         */
        binding.addNoteBTN.setOnClickListener {
            findNavController().navigate(R.id.action_mainNotesFragment_to_addNotesFragment)
        }
    }

    /**
     * Передача данных и переход на другой фрагмент по Bundle
     */
    override fun onItemClickListener(noteEntity: NoteEntity) {
        bundle.putInt("Id", noteEntity.id)
        findNavController().navigate(R.id.action_mainNotesFragment_to_editNoteFragment, bundle)
    }

}