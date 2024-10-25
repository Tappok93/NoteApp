package com.bignerdranch.android.notesapp.ui.fragments.note

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notesapp.MyApplication
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.storage.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.databinding.FragmentMainNotesBinding
import com.bignerdranch.android.notesapp.ui.adapter.NoteRecyclerViewAdapter
import com.bignerdranch.android.notesapp.ui.view_model.NoteViewModel
import javax.inject.Inject


class MainNoteFragment : Fragment(), NoteRecyclerViewAdapter.InfoNoteItemClickListener {
    private lateinit var binding: FragmentMainNotesBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NoteRecyclerViewAdapter

    @Inject
    lateinit var noteViewModel: NoteViewModel
    private val bundle = Bundle()

    /**
     * Подключаем Dagger2 к фрагменту
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNotesBinding.inflate(layoutInflater, container, false)
        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

        adapter = NoteRecyclerViewAdapter(emptyList())
        recycler.adapter = adapter
        adapter.setInfoListener(this)

        noteViewModel.allNotes.observe(
            viewLifecycleOwner
        ) { dataList ->
            dataList?.let {
                adapter.updateNoteListFromDB(it)
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

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setEnterAnim(R.anim.slide_in_right)
                .setEnterAnim(androidx.appcompat.R.anim.abc_fade_in)
                .build()
            findNavController().navigate(
                R.id.action_mainNotesFragment_to_addNotesFragment,
                null,
                navOptions
            )
        }

        /**
         * Поиск заметок по SearchView
         */
        binding.searchNoteSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filterNote(newText)
                return true
            }
        })
    }

    /**
     * Передача данных и переход на другой фрагмент по Bundle
     */
    override fun onItemClickListener(noteEntity: NoteEntity) {
        bundle.putInt("Id", noteEntity.id)
        findNavController().navigate(R.id.action_mainNotesFragment_to_editNoteFragment, bundle)
    }
}