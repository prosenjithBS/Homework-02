package com.example.homeworksecond.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeworksecond.data.HomeFragmentRepository
import com.example.homeworksecond.model.*
import com.example.homeworksecond.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
        private val homeFragmentRepository:HomeFragmentRepository) : ViewModel() {

    private val sliderModelArrayList = MutableLiveData<List<SliderModel>>()
    private val shortcutList = MutableLiveData<List<ShortcutModel>>()
    private val eshopItemList = MutableLiveData<List<EshopItemModel>>()
    private val internetItemList = MutableLiveData<List<InternetItemModel>>()
    private val compositeDisposable = CompositeDisposable()

    fun getSliderModelArrayList(): LiveData<List<SliderModel>> {
        compositeDisposable.add(homeFragmentRepository.getSliderModelArrayList()
                .delay(5,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           sliderModelArrayList.postValue(it)
                },{}))
        return sliderModelArrayList
    }

    fun getShortcutList(): LiveData<List<ShortcutModel>> {
        compositeDisposable.add(homeFragmentRepository.getShortcutList()
                .delay(getRandomNumber(0,4),TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           shortcutList.postValue(it)
                },{})
        )
        return shortcutList
    }

    fun getEshopItemList(): LiveData<List<EshopItemModel>> {
        compositeDisposable.add(homeFragmentRepository.getEshopItemList()
                .delay(getRandomNumber(0,4),TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           eshopItemList.postValue(it)
                },{})
        )
        return eshopItemList
    }

    fun getInternetItemList(): LiveData<List<InternetItemModel>> {
        compositeDisposable.add(homeFragmentRepository.getInternetItemList()
                .delay(getRandomNumber(0,4),TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                            internetItemList.value = it
                        }, {})
        )
        return internetItemList
    }


    override fun onCleared() {
        compositeDisposable.dispose()
    }

    private fun getRandomNumber(start: Int, end: Int): Long {
        require(start <= end) { "Illegal Argument" }
        return (Math.random() * (end - start + 1)).toLong() + start
    }
}