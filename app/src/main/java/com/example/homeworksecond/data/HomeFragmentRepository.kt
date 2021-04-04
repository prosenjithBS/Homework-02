package com.example.homeworksecond.data

import com.example.homeworksecond.model.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

interface HomeFragmentRepository {
    fun getParentArrayList(): Single<MutableList<ParentItemModel>>
    fun getSliderModelArrayList(): Single<MutableList<SliderModel>>
    fun getShortcutList(): Single<MutableList<ShortcutModel>>
    fun getEshopItemList(): Single<MutableList<EshopItemModel>>
    fun getInternetItemList(): Single<MutableList<InternetItemModel>>
}