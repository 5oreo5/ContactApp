package com.android.contectapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.android.contectapp.databinding.FragmentDetailContactBinding

class DetailContactFragment : DialogFragment() {

    private lateinit var binding: FragmentDetailContactBinding
    private val requestCode = 123

    override fun onResume() {
        super.onResume()
        //다이얼로그 size
        val windowManager =
            requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        val deviceHeight = size.y
        params?.width = (deviceWidth * 0.9).toInt()
        params?.height = (deviceHeight * 0.8).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailContactBinding.inflate(layoutInflater)

        arguments?.let {
            val image = it.getInt("image")
            val name = it.getString("name")
            val nickname = it.getString("nickname")
            val phone = it.getString("phone")
            val specialist = it.getString("specialist")
            val email = it.getString("email")
            val event = it.getString("event")
            val status = it.getString("status")
            binding.detailIvImage.setImageResource(image)
            binding.detailTvName.text = name
            binding.detailTvNickname.text = nickname
            binding.detailTvPhone.text = phone
            binding.detailTvSpecialist.text = specialist
            binding.detailTvEmail.text = email
            binding.detailTvEvent.text = event
            binding.detailTvStatus.text = status
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var callBtn = binding.detailBtnCall

        callBtn.setOnClickListener {
            val phone = binding.detailTvPhone.text
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
            if (checkCallPermission()) {
                makePhoneCall(intent, phone as String)
            } else {
                requestCallPermission()
            }
        }
    }
    private fun checkCallPermission(): Boolean {
        val permission = android.Manifest.permission.CALL_PHONE
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            requireContext(), permission
        )
    }

    private fun makePhoneCall(intent: Intent, phoneNumber: String) {
        intent.putExtra("phone_number", phoneNumber)
        startActivity(intent)
    }

    private fun requestCallPermission() {
        val permission = android.Manifest.permission.CALL_PHONE
        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(permission), requestCode) }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            this.requestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val phoneNumber = arguments?.getString("mobile")
                    val callIntent =
                        Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                    if (phoneNumber != null) {
                        makePhoneCall(callIntent, phoneNumber)
                    }
                } else {
                    Toast.makeText(requireContext(), "전화 걸기 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}