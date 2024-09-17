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
import com.bignerdranch.android.notesapp.utils.UtilsApp
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    @SuppressLint("NewApi")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {

                val newNote = noteViewModel.createNoteObj(
                    binding.textNote.text.toString(),
                    binding.textHeaderNote.text.toString(),
                    UtilsApp.formatDate(Date())
                )

                noteViewModel.insertNote(newNote)
                UtilsApp.sendPushInfo(
                    requireContext(),
                    R.string.TextHeaderNote.toString(),
                    R.string.TextBodyNote.toString()
                )
                binding.textNote.text.clear()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
