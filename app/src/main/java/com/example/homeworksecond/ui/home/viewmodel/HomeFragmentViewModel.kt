package com.example.homeworksecond.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeworksecond.data.HomeFragmentRepository
import com.example.homeworksecond.data.HomeFragmentRepositoryImpl
import com.example.homeworksecond.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val homeFragmentRepository:HomeFragmentRepository) : ViewModel() {

    private val parentArrayListLiveData = MutableLiveData<List<ParentItemModel>>()
    private val sliderModelArrayList = MutableLiveData<List<SliderModel>>()
    private val shortcutList = MutableLiveData<List<ShortcutModel>>()
    private val eshopItemList = MutableLiveData<List<EshopItemModel>>()
    private val internetItemList = MutableLiveData<List<InternetItemModel>>()
    private val compositeDisposable = CompositeDisposable()


    fun getParentArrayList(): LiveData<List<ParentItemModel>> {
        compositeDisposable.add(homeFragmentRepository.getParentArrayList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MutableList<ParentItemModel>>() {
                override fun onSuccess(t: MutableList<ParentItemModel>) {
                    parentArrayListLiveData.value = t
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

            })
        )

        compositeDisposable.add(homeFragmentRepository.getParentArrayList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                })
        )
        return parentArrayListLiveData
    }

    fun getSliderModelArrayList(): LiveData<List<SliderModel>> {
        compositeDisposable.add(homeFragmentRepository.getSliderModelArrayList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MutableList<SliderModel>>() {
                    override fun onSuccess(t: MutableList<SliderModel>) {
                        sliderModelArrayList.value = t
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }
                })
        )
        return sliderModelArrayList
    }

    fun getShortcutList(): LiveData<List<ShortcutModel>> {
        compositeDisposable.add(homeFragmentRepository.getShortcutList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MutableList<ShortcutModel>>(){
                    override fun onSuccess(t: MutableList<ShortcutModel>) {
                        shortcutList.value=t
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }

                })
        )
        return shortcutList
    }

    fun getEshopItemList(): LiveData<List<EshopItemModel>> {
        compositeDisposable.add(homeFragmentRepository.getEshopItemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MutableList<EshopItemModel>>(){
                    override fun onSuccess(t: MutableList<EshopItemModel>) {
                        eshopItemList.value = t
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }

                })
        )
        return eshopItemList
    }

    fun getInternetItemList(): LiveData<List<InternetItemModel>> {
        compositeDisposable.add(homeFragmentRepository.getInternetItemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<MutableList<InternetItemModel>>(){
                    override fun onSuccess(t: MutableList<InternetItemModel>) {
                        internetItemList.value = t
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }

                })
        )
        return internetItemList
    }


    override fun onCleared() {
        compositeDisposable.dispose()
    }
}