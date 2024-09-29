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


@Suppress("DEPRECATION")
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
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

    /**
     * Сохранение в sharedPreferences текста по кнопке Tollbar, установка UserName
     */
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {

                //Сохранение UserName в SharedPreference
                PreferencesBase.saveUserName(
                    requireContext(),
                    binding.nameSettingET.text.toString()
                )

                // Обновление имени пользователя в ViewModel
                (requireActivity() as MainActivity).userViewModel.
                setUserName(binding.nameSettingET.text.toString())

                //Переход на главный фрагмент при обработке нажатия.
                requireActivity().supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}