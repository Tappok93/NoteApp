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
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.databinding.EditNoteFragmentBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Ловим во фрагменте данные переданные по Bundle и присваиваем элементам фрагмента
        binding.editHeaderNoteTV.text = arguments?.getString("nameHeaderNote")
        val textBodyNote = arguments?.getString("nameBodyNote")
        binding.editBodyNoteET.setText(textBodyNote)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {

                val data = arguments?.getString("dataNote")
                val noteEntity = noteViewModel.createNoteObj(
                    binding.editHeaderNoteTV.text.toString(),
                    binding.editHeaderNoteTV.text.toString(),
                    data.toString()
                )
                noteViewModel.deleteNoteByName(noteEntity)
                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}