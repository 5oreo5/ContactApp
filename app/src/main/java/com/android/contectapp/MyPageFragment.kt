package com.android.contectapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION.SDK
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.contectapp.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding : FragmentMyPageBinding

    private val PICK_IMAGE_REQUEST = 0
    private val PERMISSION_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMyPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mypageIvImage.setOnClickListener(){
            openGallery()
        }
    }
    @SuppressLint("IntentReset")
    private fun openGallery() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (SDK_INT >= 33 && ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(android.Manifest.permission.READ_MEDIA_IMAGES)
        }

        if (SDK_INT >= 33 && ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_MEDIA_VIDEO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(android.Manifest.permission.READ_MEDIA_VIDEO)
        }

        if (SDK_INT >= 33 && ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_MEDIA_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(android.Manifest.permission.READ_MEDIA_AUDIO)
        }

        if (permissionsToRequest.isEmpty()) {
            // 이미 권한이 허용된 경우 갤러리 열기
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        } else {
            // 권한 요청
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 부여된 경우 갤러리 열기
                openGallery()
            } else {
                AlertDialog.Builder(requireContext())
                    .setMessage("갤러리 접근 권한이 거부되었습니다. 설정에서 권한을 허용해주세요.")
                    .setPositiveButton("설정으로 이동") { _, _ ->
                        // 설정 화면으로 이동하여 권한을 허용할 수 있도록 유도
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", requireContext().packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    .setNegativeButton("취소") { _, _ -> }
                    .show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            Log.d("URI", "URI:${selectedImageUri}")
            binding.mypageIvImage.setImageURI(selectedImageUri)
        }
    }
}