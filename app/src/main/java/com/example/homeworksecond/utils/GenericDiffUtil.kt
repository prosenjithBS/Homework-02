package com.example.homeworksecond.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.homeworksecond.model.*

class GenericDiffUtil<T>(
        private val oldList:ArrayList<T>,
        private val newList:ArrayList<T>
) : DiffUtil.Callback() {
    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(oldList[oldItemPosition]){
            is ShortcutModel -> (oldList as ArrayList<ShortcutModel>)[oldItemPosition].title == (newList as ArrayList<ShortcutModel>)[newItemPosition].title
            is EshopItemModel -> (oldList as ArrayList<EshopItemModel>)[oldItemPosition].title == (newList as ArrayList<EshopItemModel>)[newItemPosition].title
            is SliderModel -> (oldList as ArrayList<SliderModel>)[oldItemPosition].sliderImage == (newList as ArrayList<SliderModel>)[newItemPosition].sliderImage
            else -> (oldList as ArrayList<InternetItemModel>)[oldItemPosition].data == (newList as ArrayList<InternetItemModel>)[newItemPosition].data
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(oldList[oldItemPosition]){
            is ShortcutModel -> (oldList as ArrayList<ShortcutModel>)[oldItemPosition].title == (newList as ArrayList<ShortcutModel>)[newItemPosition].title
            is EshopItemModel -> (oldList as ArrayList<EshopItemModel>)[oldItemPosition].title == (newList as ArrayList<EshopItemModel>)[newItemPosition].title
            is SliderModel -> (oldList as ArrayList<SliderModel>)[oldItemPosition].sliderImage == (newList as ArrayList<SliderModel>)[newItemPosition].sliderImage
            else -> (oldList as ArrayList<InternetItemModel>)[oldItemPosition].data == (newList as ArrayList<InternetItemModel>)[newItemPosition].data
        }
    }
}