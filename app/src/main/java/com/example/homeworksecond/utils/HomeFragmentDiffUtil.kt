package com.example.homeworksecond.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.homeworksecond.model.ParentItemModel

class HomeFragmentDiffUtil(
        private val oldList:ArrayList<ParentItemModel>,
        private val newList:ArrayList<ParentItemModel>
):DiffUtil.Callback() {
    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].viewType == newList[newItemPosition].viewType
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].viewType == newList[newItemPosition].viewType
    }
}