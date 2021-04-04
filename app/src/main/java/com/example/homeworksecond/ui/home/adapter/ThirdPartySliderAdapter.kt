package com.example.homeworksecond.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.homeworksecond.databinding.SingleSliderItemBinding
import com.example.homeworksecond.model.SliderModel
import com.smarteist.autoimageslider.SliderViewAdapter


class ThirdPartySliderAdapter(
    private var mSliderModels: MutableList<SliderModel>
    ) : SliderViewAdapter<ThirdPartySliderAdapter.SliderAdapterVH>() {

    private lateinit var context: Context

    fun renewItems(sliderItems: MutableList<SliderModel>) {
        mSliderModels = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderModels.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderModel) {
        mSliderModels.add(sliderItem)
        notifyDataSetChanged()
    }

   override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
       context=parent.context
       val binding= SingleSliderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderAdapterVH(binding)
   }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.binding.sliderImage.setImageResource(mSliderModels[position].sliderImage)

        viewHolder.itemView.setOnClickListener{
            Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getCount() = mSliderModels.size

    inner class SliderAdapterVH(var binding: SingleSliderItemBinding) : ViewHolder(binding.root)
}