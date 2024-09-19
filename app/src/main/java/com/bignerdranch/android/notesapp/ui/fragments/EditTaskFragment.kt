package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.EditTaskFragmentBinding

@Suppress("DEPRECATION")
class EditTaskFragment : Fragment() {
    private lateinit var binding: EditTaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditTaskFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Ловим во фрагменте данные переданные по Bundle и присваиваем элементам фрагмента
        val textBodyTask = arguments?.getString("nameBodyTask")
        binding.editBodyTaskET.setText(textBodyTask)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}