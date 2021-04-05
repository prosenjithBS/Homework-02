package com.example.homeworksecond.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.SingleEshopItemBinding
import com.example.homeworksecond.databinding.SingleInternetItemBinding
import com.example.homeworksecond.databinding.SingleShortcutItemBinding
import com.example.homeworksecond.model.EshopItemModel
import com.example.homeworksecond.model.InternetItemModel
import com.example.homeworksecond.model.ShortcutModel
import com.example.homeworksecond.utils.GenericDiffUtil

class GenericRVAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val list = ArrayList<T>()

    fun addData(list: ArrayList<T>){
        val diffCallback = GenericDiffUtil(this.list,list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list.clear()
        this.list.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
    companion object{
        const val VIEW_TYPE_SHORTCUTS = 1
        const val VIEW_TYPE_ESHOP = 2
        const val VIEW_TYPE_INTERNET = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_ESHOP ->{
                EshopRVViewHolder(SingleEshopItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            VIEW_TYPE_INTERNET ->{
                InternetRVViewHolder(SingleInternetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else ->{
                ShortcutRVViewHolder(SingleShortcutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(list[position]){
            is EshopItemModel -> {
                val currentItem = list[position] as EshopItemModel
                with((holder as GenericRVAdapter<*>.EshopRVViewHolder).binding){
                    eshopProductImage.setImageResource(currentItem.image)
                    eshopProductTitle.text=currentItem.title
                    eshopProductPrice.text=currentItem.price.toString()
                    eshopProductPrePrice.text="Tk. "+currentItem.prePrice.toString()
                    eshopProductEmi.text = currentItem.emi.toString()
                    eshopProductSave.text = currentItem.save.toString()
                }
            }
            is InternetItemModel -> {
                val currentItem = list[position] as InternetItemModel
                with((holder as GenericRVAdapter<*>.InternetRVViewHolder).binding){
                    internetData.text=currentItem.data.toString()+" GB"
                    internetExtraData.text=currentItem.extraData
                    internetPts.text=currentItem.pts.toString()
                    internetDays.text=currentItem.days.toString()
                    internetTkBtn.text = "Tk "+currentItem.tk.toString()
                }
            }
            is ShortcutModel -> {
                val currentItem = list[position] as ShortcutModel
                with((holder as GenericRVAdapter<*>.ShortcutRVViewHolder).binding){
                    shortcutIcon.setImageResource(currentItem.shortcutIcon)
                    shortcutTitle.text=currentItem.title
                }
            }
        }


    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is EshopItemModel -> VIEW_TYPE_ESHOP
            is ShortcutModel -> VIEW_TYPE_SHORTCUTS
            is InternetItemModel -> VIEW_TYPE_INTERNET
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    private inner class EshopRVViewHolder(val binding: SingleEshopItemBinding):RecyclerView.ViewHolder(binding.root)

    private inner class InternetRVViewHolder(val binding:SingleInternetItemBinding):RecyclerView.ViewHolder(binding.root)

    private inner class ShortcutRVViewHolder(val binding: SingleShortcutItemBinding): RecyclerView.ViewHolder(binding.root)

}