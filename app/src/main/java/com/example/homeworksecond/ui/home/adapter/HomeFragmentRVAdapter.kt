package com.example.homeworksecond.ui.home.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.*
import com.example.homeworksecond.model.*
import com.example.homeworksecond.utils.GenericDiffUtil
import com.example.homeworksecond.utils.HomeFragmentDiffUtil
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeFragmentRVAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var context:Context
    private val list = ArrayList<ParentItemModel>()
    private val sliderItemList = ArrayList<SliderModel>()
    private val shortcutItemList = ArrayList<ShortcutModel>()
    private val eshopItemList = ArrayList<EshopItemModel>()
    private val internetItemList = ArrayList<InternetItemModel>()

    fun <T>addData(list: ArrayList<T>){
        when(list.firstOrNull()){
            is ParentItemModel -> {
                val diffCallback = HomeFragmentDiffUtil(this.list,list as ArrayList<ParentItemModel>)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                this.list.clear()
                this.list.addAll(list)
                diffResult.dispatchUpdatesTo(this)
            }
            is SliderModel -> {
                sliderItemList.clear()
                sliderItemList.addAll(list as ArrayList<SliderModel>)
                notifyDataSetChanged()
            }
            is ShortcutModel -> {
                val diffCallback = GenericDiffUtil(shortcutItemList,list as ArrayList<ShortcutModel>)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                this.shortcutItemList.clear()
                this.shortcutItemList.addAll(list)
                diffResult.dispatchUpdatesTo(this)
            }
            is EshopItemModel -> {
                val diffCallback = GenericDiffUtil(eshopItemList,list as ArrayList<EshopItemModel>)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                this.eshopItemList.clear()
                this.eshopItemList.addAll(list)
                diffResult.dispatchUpdatesTo(this)
            }
            is InternetItemModel -> {
                val diffCallback = GenericDiffUtil(internetItemList,list as ArrayList<InternetItemModel>)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                this.internetItemList.clear()
                this.internetItemList.addAll(list)
                diffResult.dispatchUpdatesTo(this)
            }

        }
    }

    companion object{
        const val VIEW_TYPE_TOP_GREETING = 1
        const val VIEW_TYPE_SLIDER = 2
        const val VIEW_TYPE_TOTAL_BALANCE = 3
        const val VIEW_TYPE_RECHARGE = 4
        const val VIEW_TYPE_SHORTCUTS = 5
        const val VIEW_TYPE_ESHOP = 6
        const val VIEW_TYPE_INTERNET = 7
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context=parent.context
        return when(viewType){
            VIEW_TYPE_TOP_GREETING->
                TopGreetingViewHolder(TopGreetingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            VIEW_TYPE_SLIDER->
                ThirdPartySliderViewHolder(SingleImageSliderThirdPartyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            VIEW_TYPE_RECHARGE->
                RechargeViewHolder(RechargeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            VIEW_TYPE_TOTAL_BALANCE->
                TotalBalanceViewHolder(TotalBalanceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            VIEW_TYPE_SHORTCUTS->
                ShortcutsViewHolder(AllShortcutItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            VIEW_TYPE_ESHOP ->
                EshopViewHolder(AllEshopItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else ->
                InternetViewHolder(AllInternetItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        when(list[position].viewType){
//            ViewType.VIEW_TYPE_SLIDER->{
//                with(holder as SliderViewHolder){
//                    binding.sliderRV.layoutManager = layoutManager
//                    binding.sliderRV.setHasFixedSize(true)
//                    val sliderItemList = DataSource.sliderItemList
//                    val sliderRVAdapter = SliderRVAdapter(sliderItemList)
//                    binding.sliderRV.adapter=sliderRVAdapter
//                }
//            }
            VIEW_TYPE_SLIDER->{
                with(holder as ThirdPartySliderViewHolder){
                    with(binding.imageSlider){
                        setSliderAdapter(ThirdPartySliderAdapter(sliderItemList))
                        setIndicatorAnimation(IndicatorAnimationType.WORM)
                        setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                        startAutoCycle()
                        autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
                        indicatorSelectedColor = Color.WHITE
                        indicatorUnselectedColor = Color.GRAY
                        scrollTimeInSec = 4
                        startAutoCycle()
                    }
                }
            }
            VIEW_TYPE_SHORTCUTS->{
                val shortcutsRVAdapter = GenericRVAdapter<ShortcutModel>()
                shortcutsRVAdapter.addData(shortcutItemList)
                with((holder as ShortcutsViewHolder).binding){
                    shortcutsRV.layoutManager = layoutManager
                    shortcutsRV.setHasFixedSize(true)
                    shortcutsRV.adapter= shortcutsRVAdapter
                }
            }
            VIEW_TYPE_ESHOP ->{
                val eshopRVAdapter = GenericRVAdapter<EshopItemModel>()
                eshopRVAdapter.addData(eshopItemList)
                with((holder as EshopViewHolder).binding){
                    eshopRV.layoutManager=layoutManager
                    eshopRV.setHasFixedSize(true)
                    eshopRV.adapter= eshopRVAdapter
                }
            }
            VIEW_TYPE_INTERNET ->{
                val internetRVAdapter = GenericRVAdapter<InternetItemModel>()
                internetRVAdapter.addData(internetItemList)
                with((holder as InternetViewHolder).binding){
                    internetRV.layoutManager=layoutManager
                    internetRV.setHasFixedSize(true)
                    internetRV.adapter=internetRVAdapter
                }
            }
        }

    }

    override fun getItemCount(): Int = list.size
    override fun getItemViewType(position: Int)= list[position].viewType

    private inner class TopGreetingViewHolder(val binding:TopGreetingBinding):RecyclerView.ViewHolder(binding.root)

//    private inner class SliderViewHolder(val binding:SliderBinding):RecyclerView.ViewHolder(binding.root)

    private inner class ThirdPartySliderViewHolder(val binding:SingleImageSliderThirdPartyBinding):RecyclerView.ViewHolder(binding.root)

    private inner class RechargeViewHolder(val binding: RechargeBinding):RecyclerView.ViewHolder(binding.root)

    private inner class TotalBalanceViewHolder(val binding: TotalBalanceBinding):RecyclerView.ViewHolder(binding.root)

    private inner class ShortcutsViewHolder(val binding: AllShortcutItemsBinding):RecyclerView.ViewHolder(binding.root)

    private inner class EshopViewHolder(val binding: AllEshopItemsBinding):RecyclerView.ViewHolder(binding.root)

    private inner class InternetViewHolder(val binding:AllInternetItemsBinding):RecyclerView.ViewHolder(binding.root)

}