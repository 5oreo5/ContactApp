package com.android.contectapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException

class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {

        return when (position) {

            0 -> DetailContactFragment()
            1 -> ContactListFragment()
            2 -> MyPageFragment()
            else -> throw IllegalArgumentException("잘못된 위치: $position")
        }

    }

}