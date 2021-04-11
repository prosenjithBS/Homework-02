package com.example.homeworksecond.datasource

import com.example.homeworksecond.R
import com.example.homeworksecond.model.*
import com.example.homeworksecond.ui.home.adapter.HomeFragmentRVAdapter
import javax.inject.Inject

class LocalDataSource @Inject constructor(){

    val sliderModelArrayList = arrayListOf(
            SliderModel(R.drawable.banner1),
            SliderModel(R.drawable.banner2),
            SliderModel(R.drawable.banner3),
            SliderModel(R.drawable.banner4),
            SliderModel(R.drawable.banner5)
    )
    val shortcutlist = arrayListOf(
            ShortcutModel(R.drawable.shortcut_balance_transfer_icon,"Transfer Balance"),
            ShortcutModel(R.drawable.shortcut_profile_icon,"Update Profile"),
            ShortcutModel(R.drawable.shortcut_complain_icon, "Lodge a complain"),
            ShortcutModel(R.drawable.shortcut_internet_icon, "Internet Packs"),
    )
    val eshopItemList = arrayListOf(
            EshopItemModel(R.drawable.samsung,"Samsung Galaxy S20 Ultra",85000,95000,10000,0.0),
            EshopItemModel(R.drawable.samsung,"Samsung Galaxy S20 Ultra",85000,95000,10000,0.0),
            EshopItemModel(R.drawable.samsung,"Samsung Galaxy S20 Ultra",85000,95000,10000,0.0),
            EshopItemModel(R.drawable.samsung,"Samsung Galaxy S20 Ultra",85000,95000,10000,0.0),
            EshopItemModel(R.drawable.samsung,"Samsung Galaxy S20 Ultra",85000,95000,10000,0.0),
            EshopItemModel(R.drawable.samsung,"Samsung Galaxy S20 Ultra",85000,95000,10000,0.0)
    )

    val internetItemList= arrayListOf(
            InternetItemModel(15.0,"99GB+99GB(4G Bonus)+512MB (Toffee)", arrayListOf("Exclusive","Only For Toffee","New"),51,30,510),
            InternetItemModel(15.0,"99GB+99GB(4G Bonus)+512MB (Toffee)", arrayListOf("Exclusive","Only For Toffee","New"),51,30,510),
            InternetItemModel(15.0,"99GB+99GB(4G Bonus)+512MB (Toffee)", arrayListOf("Exclusive","Only For Toffee","New"),51,30,510),
            InternetItemModel(15.0,"99GB+99GB(4G Bonus)+512MB (Toffee)", arrayListOf("Exclusive","Only For Toffee","New"),51,30,510)
    )
}