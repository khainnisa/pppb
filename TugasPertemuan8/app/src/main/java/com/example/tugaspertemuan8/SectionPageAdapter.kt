package com.example.tugaspertemuan8

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment()
            1 -> LoginFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
