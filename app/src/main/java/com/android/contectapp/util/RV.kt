package com.android.contectapp.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.android.contectapp.R

class RV : AppCompatActivity() {
    val datalist= mutableListOf<Item>()
    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    var log="로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_item_list)

    }
}