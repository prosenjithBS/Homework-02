package com.example.homeworksecond.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.SingleEshopItemBinding
import com.example.homeworksecond.model.EshopItemModel

class EshopRVAdapter(
        private val eshopProductList:ArrayList<EshopItemModel>
):RecyclerView.Adapter<EshopRVAdapter.EshopRVViewHolder>() {

    inner class EshopRVViewHolder(val binding:SingleEshopItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EshopRVViewHolder {
        val binding=SingleEshopItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EshopRVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EshopRVViewHolder, position: Int) {
        val currentItem = eshopProductList[position]
        with(holder.binding){
            eshopProductImage.setImageResource(currentItem.image)
            eshopProductTitle.text=currentItem.title
            eshopProductPrice.text=currentItem.price.toString()
            eshopProductPrePrice.text="Tk. "+currentItem.prePrice.toString()
            eshopProductEmi.text = currentItem.emi.toString()
            eshopProductSave.text = currentItem.save.toString()
        }

    }

    override fun getItemCount() = eshopProductList.size
}