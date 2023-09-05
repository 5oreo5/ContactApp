package com.android.contectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.contectapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val tabTextList = listOf("detail_page","contacts", "my_page")
    private val tabIconList = listOf(R.drawable.tab_iv_detailpage,R.drawable.tab_iv_contacts,R.drawable.tab_iv_mypage)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainViewPager.adapter = MainAdapter(this)

        TabLayoutMediator(binding.mainTabLayout,binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
            tab.setIcon(tabIconList[position])
        }.attach()
    }
}