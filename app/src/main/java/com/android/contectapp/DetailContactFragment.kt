package com.android.contectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.contectapp.databinding.FragmentDetailContactBinding
import com.android.contectapp.databinding.FragmentMyPageBinding

class DetailContactFragment : Fragment() {

    private lateinit var binding : FragmentDetailContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailContactBinding.inflate(layoutInflater)
        arguments?.let {
            val name = it.getString("name")
            val nickname = it.getString("nickname")
            val mobile = it.getString("mobile")
            val specialist = it.getString("specialist")
            val email = it.getString("email")
            val event = it.getString("event")
            val status = it.getString("status")
            binding.detailTvName.text = name
            binding.detailTvNickname.text = nickname
            binding.detailTvPhone.text = mobile
            binding.detailTvSpecialist.text = specialist
            binding.detailTvEmail.text = email
            binding.detailTvEvent.text = event
            binding.detailTvStatus.text = status
        }
        return binding.root

    }
}