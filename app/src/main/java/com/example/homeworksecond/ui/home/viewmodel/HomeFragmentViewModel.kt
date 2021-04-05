package com.example.homeworksecond.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeworksecond.data.HomeFragmentRepository
import com.example.homeworksecond.data.HomeFragmentRepositoryImpl
import com.example.homeworksecond.model.*
import com.example.homeworksecond.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
        private val homeFragmentRepository:HomeFragmentRepository) : ViewModel() {

    private val _parentArrayListLiveData = MutableLiveData< Resource<List<ParentItemModel>>>()
    private val sliderModelArrayList = MutableLiveData<List<SliderModel>>()
    private val shortcutList = MutableLiveData<List<ShortcutModel>>()
    private val eshopItemList = MutableLiveData<List<EshopItemModel>>()
    private val internetItemList = MutableLiveData<List<InternetItemModel>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchParentArrayList()
    }

    private fun fetchParentArrayList() {
        compositeDisposable.add(homeFragmentRepository.getParentArrayList()
            .delay(2,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       _parentArrayListLiveData.postValue(Resource.success(it))
            },{
                _parentArrayListLiveData.postValue(Resource.error("Something went wrong!",null))
            })
        )
    }

    fun getSliderModelArrayList(): LiveData<List<SliderModel>> {
        compositeDisposable.add(homeFragmentRepository.getSliderModelArrayList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           sliderModelArrayList.postValue(it)
                },{}))
        return sliderModelArrayList
    }

    fun getShortcutList(): LiveData<List<ShortcutModel>> {
        compositeDisposable.add(homeFragmentRepository.getShortcutList()
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           eshopItemList.postValue(it)
                },{})
        )
        return eshopItemList
    }

    fun getInternetItemList(): LiveData<List<InternetItemModel>> {
        compositeDisposable.add(
            homeFragmentRepository.getInternetItemList()
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

    fun getParentArrayList() : LiveData<Resource<List<ParentItemModel>>>{
        return _parentArrayListLiveData
    }
}