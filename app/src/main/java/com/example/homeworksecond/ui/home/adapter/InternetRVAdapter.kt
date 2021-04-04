package com.example.homeworksecond.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.SingleInternetItemBinding
import com.example.homeworksecond.model.InternetItemModel

class InternetRVAdapter(
    private val internetItemList:ArrayList<InternetItemModel>
) : RecyclerView.Adapter<InternetRVAdapter.InternetRVViewHolder>(){
    inner class InternetRVViewHolder(val binding:SingleInternetItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InternetRVViewHolder {
        val binding = SingleInternetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return InternetRVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InternetRVViewHolder, position: Int) {
        val currentItem = internetItemList[position]
        with(holder.binding){
            internetData.text=currentItem.data.toString()+" GB"
            internetExtraData.text=currentItem.extraData
            internetPts.text=currentItem.pts.toString()
            internetDays.text=currentItem.days.toString()
            internetTkBtn.text = "Tk "+currentItem.tk.toString()
        }
    }

    override fun getItemCount()=internetItemList.size
}