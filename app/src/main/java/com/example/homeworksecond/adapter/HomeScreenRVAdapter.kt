package com.example.homeworksecond.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.data.DataSource
import com.example.homeworksecond.databinding.*
import com.example.homeworksecond.model.ParentItemModel
import com.example.homeworksecond.utils.ViewType
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeScreenRVAdapter(
    private val context:Context,
    private val list:ArrayList<ParentItemModel>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.VIEW_TYPE_TOP_GREETING->
                TopGreetingViewHolder(TopGreetingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            ViewType.VIEW_TYPE_SLIDER->
                ThirdPartySliderViewHolder(ImageSliderThirdPartyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            ViewType.VIEW_TYPE_RECHARGE->
                RechargeViewHolder(RechargeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            ViewType.VIEW_TYPE_TOTAL_BALANCE->
                TotalBalanceViewHolder(TotalBalanceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            ViewType.VIEW_TYPE_SHORTCUTS->
                ShortcutsViewHolder(ShortcutsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            ViewType.VIEW_TYPE_ESHOP ->
                EshopViewHolder(EshopItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else ->
                InternetViewHolder(InternetItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        when(list[position].viewType){
//            ViewType.VIEW_TYPE_SLIDER->{
//                with(holder as SliderViewHolder){
//                    binding.sliderRV.layoutManager = layoutManager
//                    binding.sliderRV.setHasFixedSize(true)
//                    val sliderModelArrayList = DataSource.sliderModelArrayList
//                    val sliderRVAdapter = SliderRVAdapter(sliderModelArrayList)
//                    binding.sliderRV.adapter=sliderRVAdapter
//                }
//            }
            ViewType.VIEW_TYPE_SLIDER->{
                val sliderModelArrayList = DataSource.sliderModelArrayList
                with(holder as ThirdPartySliderViewHolder){
                    with(binding.imageSlider){
                        setSliderAdapter(ThirdPartySliderAdapter(context,sliderModelArrayList))
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

            ViewType.VIEW_TYPE_SHORTCUTS->{
                with(holder as ShortcutsViewHolder){
                    binding.shortcutsRV.layoutManager = layoutManager
                    binding.shortcutsRV.setHasFixedSize(true)
                    val shortcutlist = DataSource.shortcutlist
                    val shortcutsRVAdapter = ShortcutsRVAdapter(shortcutlist)
                    binding.shortcutsRV.adapter= shortcutsRVAdapter
                }
            }

            ViewType.VIEW_TYPE_ESHOP ->{
                val eshopItemList = DataSource.eshopItemList
                val eshopRVAdapter = EshopRVAdapter(eshopItemList)
                eshopRVAdapter.notifyDataSetChanged()
                with((holder as EshopViewHolder).binding){
                    eshopRV.layoutManager=layoutManager
                    eshopRV.setHasFixedSize(true)
                    eshopRV.adapter= eshopRVAdapter
                }
            }
            ViewType.VIEW_TYPE_INTERNET ->{
                val internetItemList = DataSource.internetItemList
                val internetRVAdapter = InternetRVAdapter(internetItemList)
                internetRVAdapter.notifyDataSetChanged()
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


//    private inner class SliderViewHolder(val binding:SliderBinding):RecyclerView.ViewHolder(binding.root){
//
//    }

    private inner class ThirdPartySliderViewHolder(val binding:ImageSliderThirdPartyBinding):RecyclerView.ViewHolder(binding.root)

    private inner class RechargeViewHolder(val binding: RechargeBinding):RecyclerView.ViewHolder(binding.root)

    private inner class TotalBalanceViewHolder(val binding: TotalBalanceBinding):RecyclerView.ViewHolder(binding.root)

    private inner class ShortcutsViewHolder(val binding: ShortcutsBinding):RecyclerView.ViewHolder(binding.root)

    private inner class EshopViewHolder(val binding: EshopItemsBinding):RecyclerView.ViewHolder(binding.root)

    private inner class InternetViewHolder(val binding:InternetItemsBinding):RecyclerView.ViewHolder(binding.root)
}