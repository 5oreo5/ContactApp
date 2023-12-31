package com.android.contectapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.contectapp.ui.ContactListFragment
import com.android.contectapp.ui.MyPageFragment
import java.lang.IllegalArgumentException

class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> ContactListFragment()
            1 -> MyPageFragment()
            else -> throw IllegalArgumentException("잘못된 위치: $position")
        }

    }

}