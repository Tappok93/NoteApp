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
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddNotesBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import com.bignerdranch.android.notesapp.utils.UtilsApp
import kotlinx.coroutines.launch
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

    /**
     * Раздуваем созданное меню
     */
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

                //Вставка заметки в БД
                noteViewModel.insertNote(
                    noteViewModel.createNoteObj(
                        binding.textNote.text.toString(),
                        binding.textHeaderNote.text.toString(),
                        UtilsApp.formatDate(Date())
                    )
                )

                //Отправка уведомления
                noteViewModel.sendPushNote(
                    this,
                    getString(R.string.TextHeaderNote),
                    getString(R.string.TextBodyNote)
                )

                //Очищаем поле ввода
                binding.textNote.text.clear()

                //Переход на предыдущий фрагмент стека
                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
