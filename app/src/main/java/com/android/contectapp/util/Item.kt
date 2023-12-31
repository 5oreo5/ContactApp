package com.android.contectapp.util

import android.os.Parcelable
import com.android.contectapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val image: Int,
    val name: String,
    val nickname: String,
    val phone: String,
    val specialist: String,
    val email: String,
    val event: String,
    val status: String,
) : Parcelable


object NewListRepository {
   private val dataList = mutableListOf<Item>()

    fun addAndSort(newItem: Item) {
        dataList.add(newItem)
        dataList.sortBy { it.name }
    }

    fun getNewList(): MutableList<Item> {

        dataList.add(
            Item(
                R.drawable.detail_iv_1,
                "이익준",
                "익듀니",
                "010-1111-1111",
                "간담췌외과",
                "aaaa@yulje.com",
                "5분 뒤",
                "매주 목요일 9시 ~ 11시 세미나로 인해 통화 어렵습니다.",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_2,
                "안정원",
                "워니",
                "010-1111-1112",
                "소아외과",
                "bbbb@yulje.com",
                "10분 뒤",
                "언제든 통화 가능합니다 ^^",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_3,
                "김준완",
                "와니",
                "010-1111-1113",
                "흉부외과",
                "cccc@yulje.com",
                "5분 뒤",
                "긴급 콜 아님 전화 하지 마세요.",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_4,
                "양석형",
                "양양",
                "010-1111-1114",
                "산부인과",
                "dddd@yulje.com",
                "5분 뒤",
                "21시 이후 통화 불가합니다~",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_5,
                "채송화",
                "천사",
                "010-1111-1115",
                "신경외과",
                "eeee@yulje.com",
                "30분 뒤",
                "월, 수, 금 본원 / 화, 목 분원",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_6,
                "장겨울",
                "여름",
                "010-1111-1116",
                "간담췌외과",
                "ffff@yulje.com",
                "10분 뒤",
                "전화 가능",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_7,
                "도재학",
                "도재",
                "010-1111-1117",
                "흉부외과",
                "gggg@yulje.com",
                "5분 뒤",
                "언제든 전화 주세요",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_8,
                "추민하",
                "추추",
                "010-1111-1118",
                "산부인과",
                "hhhh@yulje.com",
                "10분 뒤",
                "전화 주세요!",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_9,
                "천명태",
                "동태",
                "010-1111-1119",
                "흉부외과",
                "iiii@yulje.com",
                "30분 뒤",
                "전화 불가",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_10,
                "주종수",
                "쫑",
                "010-1111-1120",
                "율제재단 이사장",
                "jjjj@yulje.com",
                "OFF",
                "내선 777",
            )
        )
        dataList.add(
            Item(
                R.drawable.detail_iv_11,
                "주전",
                "주전자",
                "010-1111-1121",
                "율제병원장",
                "kkkk@yulje.com",
                "OFF",
                "내선 805",
            )
        )
        dataList.add(
            Item(
                image = R.drawable.detail_iv_12,
                name = "용석민",
                nickname = "드래곤스톤",
                phone = "010-1111-1122",
                specialist = "신경외과",
                email = "llll@yulje.com",
                event = "OFF",
                status = "출장 중 입니다.",
            )
        )
        return dataList
    }
}