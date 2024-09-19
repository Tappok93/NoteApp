package com.bignerdranch.android.notesapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddNotesBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import com.bignerdranch.android.notesapp.utils.UtilsApp
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Date


@Suppress("DEPRECATION")
class AddNoteFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentAddNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        binding = FragmentAddNotesBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Скрываем BottomNavigationView
        val bottomNavView = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.GONE)
    }

    override fun onDestroy() {
        super.onDestroy()

        //Отображаем BottomNavigationView
        val bottomNavView = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.VISIBLE)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NewApi")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {

                //Проверка разрешения на отправку уведомлений
                if (context?.let {
                        ContextCompat.checkSelfPermission(
                            it,
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    // Разрешение на отправку уведомлений не предоставлено, показываем диалоговое окно с запросом на предоставление разрешения
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                    return true
                }

                // Создаём объект новой заметки
                val newNote = noteViewModel.createNoteObj(
                    binding.textNote.text.toString(),
                    binding.textHeaderNote.text.toString(),
                    UtilsApp.formatDate(Date())
                )
                //Отправка уведомления
                noteViewModel.insertNote(newNote)
                UtilsApp.sendPushInfo(
                    requireContext(),
                   getString(R.string.TextHeaderNote),
                   getString(R.string.TextBodyNote)
                )
                binding.textNote.text.clear()

                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
