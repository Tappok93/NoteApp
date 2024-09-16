package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.shared_preferences.PreferencesBase
import com.bignerdranch.android.notesapp.databinding.FragmentSettingBinding
import com.bignerdranch.android.notesapp.databinding.HeaderMenuBinding


class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var bindingMenu: HeaderMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        bindingMenu = HeaderMenuBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Сохранение в sharedPreferences текста по кнопке Tollbar, установка UserName
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                PreferencesBase.saveUserName(requireContext(), binding.nameSettingET.text.toString())
               // bindingMenu.textView2.text = PreferencesBase.getText(requireContext())
                binding.nameSettingET.setText("")
                //popBackStack - попробовать прокинуть
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}