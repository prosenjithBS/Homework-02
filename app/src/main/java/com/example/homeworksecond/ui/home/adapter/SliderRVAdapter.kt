package com.example.homeworksecond.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.SingleSliderItemBinding
import com.example.homeworksecond.model.SliderModel

class SliderRVAdapter (
        private val sliderModelArrayList: ArrayList<SliderModel>
) : RecyclerView.Adapter<SliderRVAdapter.SliderRVViewHolder>(){

    inner class SliderRVViewHolder(var binding:SingleSliderItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderRVViewHolder {
        val binding = SingleSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderRVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderRVViewHolder, position: Int) {
        val currentItem = sliderModelArrayList[position]
        holder.binding.sliderImage.setImageResource(currentItem.sliderImage)
    }

    override fun getItemCount() = sliderModelArrayList.size

}