package com.android.contectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager

class recyclerview_item_list : AppCompatActivity() {
    val datalist= mutableListOf<Item>()
    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    var log="로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_item_list)




        datalist.add(Item("이익준","010-1111-1111","aaaa@yulje.com","간담췌외과"))
        datalist.add(Item("안정원","010-1111-1112","bbbb@yulje.com","소아외과"))
        datalist.add(Item("김준완","010-1111-1113","cccc@yulje.com","흉부외과"))
        datalist.add(Item("양석형","010-1111-1114","dddd@yulje.com","산부인과"))
        datalist.add(Item("채송화","010-1111-1115","eeee@yulje.com","신경외과"))
        datalist.add(Item("장겨울","010-1111-1116","ffff@yulje.com","간담췌외과"))
        datalist.add(Item("도재학","010-1111-1117","gggg@yulje.com","흉부외과"))
        datalist.add(Item("추민하","010-1111-1118","hhhh@yulje.com","산부인과"))
        datalist.add(Item("천명태","010-1111-1119","iiii@yulje.com","간담췌외과"))
        datalist.add(Item("주종수","010-1111-1120","jjjj@yulje.com","율제재단 이사장"))
        datalist.add(Item("주전","010-1111-1121","kkkk@yulje.com","율제병원장"))






    }
}