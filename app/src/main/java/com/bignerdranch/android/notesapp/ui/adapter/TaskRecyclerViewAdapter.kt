package com.bignerdranch.android.notesapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.room_database.entitys.TaskEntity
import com.bignerdranch.android.notesapp.databinding.ElementRecyclerTaskBinding

class TaskRecyclerViewAdapter(private var myListTask: List<TaskEntity>) :
    RecyclerView.Adapter<TaskRecyclerViewAdapter.ItemViewHolder>() {
    private var infoListener: InfoTaskItemClickListener? = null

    fun setInfoListener(listener: InfoTaskItemClickListener) {
        infoListener = listener
    }

    /**
     * Интерфейс для обработки нажатий на элементы списка
     */
    interface InfoTaskItemClickListener {
        fun onItemClickListener(taskEntity: TaskEntity)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = ElementRecyclerTaskBinding.bind(itemView)

        fun setData(baseTask: TaskEntity, listener: InfoTaskItemClickListener?) {
            bindingAdapter.textTaskElement.text = baseTask.nameTask

            bindingAdapter.elementTaskRV.setOnClickListener {
                listener?.onItemClickListener(baseTask)
            }
        }
    }

    /**
     * Создание холдера по созданному XML элементу
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_recycler_task, parent, false)
        return ItemViewHolder(inflater)
    }

    /**
     * Счётчик количеста отображаемых элементов в списке
     */
    override fun getItemCount(): Int {
        return myListTask.size
    }

    /**
     * Заполнение холдера данными по созданному шаблону XML
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val baseCityItem = myListTask[position]
        holder.setData(baseCityItem, infoListener)
    }

    /**
     * Обновление списка
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listItem: List<TaskEntity>) {
        myListTask = listItem
        notifyDataSetChanged()
    }
}