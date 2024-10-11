package com.bignerdranch.android.notesapp.ui.fragments.note

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.MyApplication
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddNotesBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import com.bignerdranch.android.notesapp.utils.UtilsApp
import java.util.Date
import javax.inject.Inject

@Suppress("DEPRECATION")
class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNotesBinding

    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
