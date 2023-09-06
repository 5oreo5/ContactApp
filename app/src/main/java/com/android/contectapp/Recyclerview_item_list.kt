package com.android.contectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher

class Recyclerview_item_list : AppCompatActivity() {
    val datalist= mutableListOf<Item>()
    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    var log="로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_item_list)

    }
}