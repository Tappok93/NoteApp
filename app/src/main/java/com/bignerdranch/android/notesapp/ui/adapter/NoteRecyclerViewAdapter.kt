package com.bignerdranch.android.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.databinding.ElementRecyclerNoteBinding

class NoteRecyclerViewAdapter(private var myListNote: List<NoteEntity>) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.ItemViewHolder>() {
    private var infoListener: InfoNoteItemClickListener? = null
    private var startListNote: List<NoteEntity> = listOf()

    fun setInfoListener(listener: InfoNoteItemClickListener) {
        infoListener = listener
    }

    /**
     * Интерфейс для обработки нажатий на элементы списка
     */
    interface InfoNoteItemClickListener {
        fun onItemClickListener(noteEntity: NoteEntity)
    }

    /**
     * Фильтрация списка заметок по вводу Searchview
     */
    fun filterNote(query: String?) {
        if (query.isNullOrEmpty()) {
            updateList(startListNote)
        } else {
            val filterListNote = startListNote.filter {
                it.nameNote.contains(query, ignoreCase = true) ||
                        it.date.contains(query, ignoreCase = true) ||
                        it.nameHeaderNote.contains(query, ignoreCase = true)
            }
            updateList(filterListNote)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = ElementRecyclerNoteBinding.bind(itemView)

        fun setData(baseNote: NoteEntity, listener: InfoNoteItemClickListener?) {
            bindingAdapter.textNoteElement.text = baseNote.nameNote
            bindingAdapter.textHeaderElementNote.text = baseNote.nameHeaderNote
            bindingAdapter.dataElementNote.text = baseNote.date

            bindingAdapter.elementNoteRV.setOnClickListener {
                listener?.onItemClickListener(baseNote)
            }
        }
    }

    /**
     * Создание холдера по созданному XML элементу
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_recycler_note, parent, false)
        return ItemViewHolder(inflater)
    }

    /**
     * Счётчик количеста отображаемых элементов в списке
     */
    override fun getItemCount(): Int {
        return myListNote.size
    }

    /**
     * Заполнение холдера данными по созданному шаблону XML
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val baseCityItem = myListNote[position]
        holder.setData(baseCityItem, infoListener)
    }

    /**
     * Обновление списка
     */
    private fun updateList(listItem: List<NoteEntity>) {
        myListNote = listItem
        notifyDataSetChanged()
    }

    /**
     * Обновление списка заметок
     */
    fun updateNoteListFromDB(listItem: List<NoteEntity>) {
        updateList(listItem)
        startListNote = myListNote.map { it.copy() }.toMutableList()
    }
}