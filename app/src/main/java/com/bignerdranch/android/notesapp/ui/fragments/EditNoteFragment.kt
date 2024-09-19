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
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.databinding.EditNoteFragmentBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class EditNoteFragment : Fragment() {
    private lateinit var binding: EditNoteFragmentBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditNoteFragmentBinding.inflate(layoutInflater, container, false)
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        setHasOptionsMenu(true)

        //Ловим во фрагменте данные переданные по Bundle и присваиваем элементам фрагмента
        val id = arguments?.getInt("Id")
        lifecycleScope.launch {
            id?.let {
                val objectNote = noteViewModel.getObjectNoteById(it)
                binding.editHeaderNoteTV.text = objectNote?.nameHeaderNote ?: "No Header"
                val textBodyNote = objectNote?.nameNote
                binding.editBodyNoteET.setText(textBodyNote)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Раздуваем созданное меню для удаления заметки
     */
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Удаление заметки за базы данных
     */
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                lifecycleScope.launch {
                    arguments?.getInt("Id")?.let { noteViewModel.deleteObjectNoteByIdUseCase(it) }
                }
                requireActivity().supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}