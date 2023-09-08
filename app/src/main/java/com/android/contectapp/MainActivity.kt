package com.android.contectapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    lateinit var tabLayout: TabLayout
    private val mainAdapter = MainAdapter(this)
    private val tabTextList = listOf("contacts", "my_page")
    private val tabIconList = listOf(R.drawable.tab_iv_contacts,R.drawable.tab_iv_mypage)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // READ_CONTACTS 권한이 허용된 경우 연락처 데이터를 불러옴
                loadContacts()
            } else {
                // READ_CONTACTS 권한이 거부된 경우 사용자에게 권한이 필요하다고 알려줄 수 있음
                // 예를 들어, 사용자에게 AlertDialog를 표시하여 권한 요청 메시지를 표시할 수 있음
                showPermissionDeniedDialog()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainViewPager.adapter = mainAdapter
        tabLayout = binding.mainTabLayout

        // READ_CONTACTS 권한 확인 및 요청
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // READ_CONTACTS 권한이 이미 허용된 경우 연락처 데이터를 불러옴
            loadContacts()
        } else {
            // READ_CONTACTS 권한을 요청
            requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
        }
        TabLayoutMediator(binding.mainTabLayout,binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
            tab.setIcon(tabIconList[position])
        }.attach()
    }
    private fun loadContacts() {
        // 연락처 데이터를 불러오는 작업을 수행
        lifecycleScope.launch(Dispatchers.IO) {
            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )


            cursor?.use { cursor ->
                while (cursor.moveToNext()) {
                    val contactName =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                    val contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))

                    val phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(contactId),
                        null
                    )

                    phoneCursor?.use { phoneCursor ->
                        while (phoneCursor.moveToNext()) {
                            val phoneNumber =
                                phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            Log.d("ContactListFragment", "연락처 이름: $contactName, 연락처 번호: $phoneNumber")
                            val newItem = Item(
                                R.drawable.tab_iv_mypage_fill,
                                contactName,
                                "",
                                phoneNumber,
                                "",
                                "",
                                "",
                                "연락 가능 시간대 : 9시 ~ 23시"
                            )
                            NewListRepository.addAndSort(newItem)

                        }
                    }
                    phoneCursor?.close()
                }
            }
            cursor?.close()
        }
    }

    private fun showPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("권한 필요")
            .setMessage("연락처를 읽기 위해 READ_CONTACTS 권한이 필요합니다. 권한을 부여하려면 설정으로 이동하세요.")
            .setPositiveButton("설정으로 이동") { _, _ ->
                // 사용자를 앱 설정으로 이동시키는 인텐트를 생성
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("나중에") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}