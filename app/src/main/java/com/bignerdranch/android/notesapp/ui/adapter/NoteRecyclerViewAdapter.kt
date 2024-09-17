package com.bignerdranch.android.notesapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.NoteEntity
import com.bignerdranch.android.notesapp.databinding.ElementRecyclerNoteBinding

class NoteRecyclerViewAdapter(private var myListNote: List<NoteEntity>) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = ElementRecyclerNoteBinding.bind(itemView)

        fun setData(baseNote: NoteEntity) {
            bindingAdapter.dataElementNote.text = baseNote.date
            bindingAdapter.textNoteElement.text = baseNote.nameNote
            bindingAdapter.textHeaderElementNote.text = baseNote.nameHeaderNote
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
        holder.setData(baseCityItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listItem: List<NoteEntity>) {
        myListNote = listItem
        notifyDataSetChanged()
    }
}