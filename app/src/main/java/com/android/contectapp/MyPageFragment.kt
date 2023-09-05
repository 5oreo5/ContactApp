package com.android.contectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.contectapp.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMyPageBinding.inflate(layoutInflater)

        // 추후에 contactlistfragment에서 putstring(setOnItemClickListener)
        arguments?.let {
            val name = it.getString("name")
            val nickname = it.getString("nickname")
            val mobile = it.getString("mobile")
            val specialist = it.getString("specialist")
            val email = it.getString("email")
            val biography = it.getString("biography")
            val status = it.getString("status")
            binding.mypageTvName.text = name
            binding.mypageTvNickname.text = nickname
            binding.mypageTvPhone.text = mobile
            binding.mypageTvSpecialist.text = specialist
            binding.mypageTvEmail.text = email
            binding.mypageTvBiography.text = biography
            binding.mypageTvStatus.text = status
        }
        return binding.root
    }
}