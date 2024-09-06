package com.bignerdranch.android.notesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.notesapp.R
import com.bignerdranch.android.notesapp.databinding.FragmentMainNotesBinding


class MainNotesFragment : Fragment() {
    private lateinit var binding: FragmentMainNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNotesBinding.inflate(layoutInflater, container, false)
        // Отключаем свап для вызова меню настроек
        binding.drawerLayoutView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Нажатие кнопки [Добавить заметку]
         */
        binding.addNoteBTN.setOnClickListener {
            findNavController().navigate(R.id.action_mainNotesFragment_to_addNotesFragment)
        }

        /**
         * Нажатие кнопки [Меню]
         */
        binding.menuSettingIV.setOnClickListener{
               binding.drawerLayoutView.openDrawer(GravityCompat.START)
        }

        /**
         * Нажатие кнопки [Настройки] в меню
         */
        binding.menuNavView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.nav_item_one) {
                findNavController().navigate(R.id.action_mainNotesFragment_to_settingFragment)
            }
            binding.drawerLayoutView.closeDrawer(GravityCompat.START)
            true
        }
    }


}