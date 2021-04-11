package com.example.homeworksecond.data

import com.example.homeworksecond.datasource.LocalDataSource
import com.example.homeworksecond.model.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Inject

class HomeFragmentRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource): HomeFragmentRepository {

    override fun getSliderModelArrayList(): Single<MutableList<SliderModel>> {
        return localDataSource.sliderModelArrayList.toObservable().toList()
    }

    override fun getShortcutList(): Single<MutableList<ShortcutModel>> {
        return localDataSource.shortcutlist.toObservable().toList()
    }

    override fun getEshopItemList(): Single<MutableList<EshopItemModel>> {
        return localDataSource.eshopItemList.toObservable().toList()
    }

    override fun getInternetItemList(): Single<MutableList<InternetItemModel>> {
        return localDataSource.internetItemList.toObservable().toList()
    }

}