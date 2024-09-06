package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentAddNotesBinding
import com.bignerdranch.android.notesapp.databinding.FragmentMainTasksBinding


class MainTasksFragment : Fragment() {
    private lateinit var binding: FragmentMainTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainTasksBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTaskBTN.setOnClickListener {
            findNavController().navigate(R.id.action_mainTasksFragment_to_addTasksFragment)
        }
    }

}