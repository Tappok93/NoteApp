package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.data.database.shared_preferences.PreferencesBase
import com.bignerdranch.android.notesapp.databinding.FragmentSettingBinding
import com.bignerdranch.android.notesapp.utils.UtilsApp
import com.google.android.material.bottomnavigation.BottomNavigationView


@Suppress("DEPRECATION")
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    //private lateinit var bindingMenu: HeaderMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        //  bindingMenu = HeaderMenuBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Скрываем BottomNavigationView
        val bottomNavView = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.GONE)
    }

    override fun onDestroy() {
        super.onDestroy()

        //Отображаем BottomNavigationView
        val bottomNavView = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        UtilsApp.bottomNavVisibility(bottomNavView, View.VISIBLE)
    }

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

                //Получение доступа к элементу NavigationDrawer
                val textUserName =
                    LayoutInflater.from(requireContext()).inflate(R.layout.header_menu, null)
                        .findViewById<TextView>(R.id.textUserName)

                //Сохранение UserName в SharedPreference
                PreferencesBase.saveUserName(
                    requireContext(),
                    binding.nameSettingET.text.toString()
                )
                //Получения UserName из SharedPreference и назначение элементу UseName
                textUserName.text = PreferencesBase.getUserName(requireContext())
                binding.nameSettingET.setText("")
                //Переход на главный фрагмент при обработке нажатия.
                requireActivity().supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}