package com.bignerdranch.android.notesapp.ui.fragments

import android.annotation.SuppressLint
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
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import java.util.Date


class AddNotesFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentAddNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        binding = FragmentAddNotesBinding.inflate(layoutInflater, container, false)
        noteViewModel.createNotificationChannel(requireContext())
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("NewApi")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                val textNote = binding.textNote.text.toString()
                val dataNote = Date()
                val newNote = noteViewModel.createNoteObj(textNote, dataNote)

                noteViewModel.insertNote(newNote)
                noteViewModel.sendPushInfo(requireContext())
                binding.textNote.text.clear()


                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
