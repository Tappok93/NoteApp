package com.bignerdranch.android.notesapp.ui.fragments.note

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.notesapp.MyApplication
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.EditNoteFragmentBinding
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import javax.inject.Inject

@Suppress("DEPRECATION")
class EditNoteFragment : Fragment() {
    private lateinit var binding: EditNoteFragmentBinding

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
        binding = EditNoteFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Ловим во фрагменте данные переданные по Bundle и присваиваем элементам фрагмента
         */
        val id = arguments?.getInt("Id")
        noteViewModel.createAndAssignNote(id ?: 0) { note ->
            val textBodyTask = note.nameNote
            binding.editBodyNoteET.setText(textBodyTask)
            binding.editHeaderNoteTV.text = note.nameHeaderNote
        }

        /**
         * Обработка кнопки [Сохранить]
         */
        binding.saveEditNoteBTN.setOnClickListener {
            noteViewModel.updateObjectNoteByIdUseCase(
                binding.editBodyNoteET.text.toString(),
                id!!
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
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
     * Удаление заметки за базы данных по нажатию на элемент меню
     */
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                arguments?.getInt("Id")?.let { deleteNoteDialog(it) }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Логика удаления заметки
     */
    private fun deleteNoteDialog(id: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Вы уверены, что хотите удалить заметку ?")
            .setItems(arrayOf("Да", "Нет")) { dialog, which ->
                when (which) {
                    0 -> {
                        noteViewModel.deleteObjectNoteByIdUseCase(id)
                        noteViewModel.sendPushNote(
                            this,
                            getString(R.string.TextHeaderNote),
                            getString(R.string.TextBodyNoteDelete)
                        )
                        requireActivity().supportFragmentManager.popBackStack()
                    }

                    1 -> {
                        dialog.dismiss()
                    }
                }
            }.show()
    }
}
