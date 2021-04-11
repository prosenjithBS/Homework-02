package com.example.homeworksecond.ui.home.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.*
import com.example.homeworksecond.model.*
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeFragmentRVAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var context:Context
    private val FADE_DURATION = 1000
    private var homeItems = mutableListOf<Pair<Int,Any?>>(
            Pair(1,null),
            Pair(3,null),
            Pair(4,null)
    )

    fun addHomeItems(obj: Any, itemType: Int) {
        var previousIndex = -1
        homeItems.forEach {
            if (it.first == itemType) {
                previousIndex = homeItems.indexOf(it)
            }
        }

        if (previousIndex == -1) {
            val pairObject = Pair(itemType, obj)
            homeItems.add(pairObject)
            homeItems.sortBy { it.first }
            val currentPosition = homeItems.indexOf(pairObject)
            notifyItemInserted(currentPosition)

        } else {
            homeItems[previousIndex] = Pair(itemType, obj)
            notifyItemChanged(previousIndex)
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
            VIEW_TYPE_TOP_GREETING ->
                TopGreetingViewHolder(TopGreetingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_SLIDER ->
                ThirdPartySliderViewHolder(SingleImageSliderThirdPartyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_RECHARGE ->
                RechargeViewHolder(RechargeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_TOTAL_BALANCE ->
                TotalBalanceViewHolder(TotalBalanceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_SHORTCUTS ->
                ShortcutsViewHolder(AllShortcutItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_ESHOP ->
                EshopViewHolder(AllEshopItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else ->
                InternetViewHolder(AllInternetItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        when(getItemViewType(position)){
            VIEW_TYPE_SLIDER -> {
                setFadeAnimation((holder as ThirdPartySliderViewHolder).binding.root)
                with(holder) {
                    with(binding.imageSlider) {
                        setSliderAdapter(ThirdPartySliderAdapter(homeItems[position].second as ArrayList<SliderModel>))
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
            VIEW_TYPE_SHORTCUTS -> {
                setFadeAnimation((holder as ShortcutsViewHolder).binding.root)
                val shortcutsRVAdapter = GenericRVAdapter<ShortcutModel>()
                shortcutsRVAdapter.addData(homeItems[position].second as ArrayList<ShortcutModel>)
                with(holder.binding) {
                    shortcutsRV.layoutManager = layoutManager
                    shortcutsRV.setHasFixedSize(true)
                    shortcutsRV.adapter = shortcutsRVAdapter
                }
            }
            VIEW_TYPE_ESHOP -> {
                setFadeAnimation((holder as EshopViewHolder).binding.root)
                val eshopRVAdapter = GenericRVAdapter<EshopItemModel>()
                eshopRVAdapter.addData(homeItems[position].second as ArrayList<EshopItemModel>)
                with(holder.binding) {
                    eshopRV.layoutManager = layoutManager
                    eshopRV.setHasFixedSize(true)
                    eshopRV.adapter = eshopRVAdapter
                }
            }
            VIEW_TYPE_INTERNET -> {
                setFadeAnimation((holder as InternetViewHolder).binding.root)
                val internetRVAdapter = GenericRVAdapter<InternetItemModel>()
                internetRVAdapter.addData(homeItems[position].second as ArrayList<InternetItemModel>)
                with(holder.binding) {
                    internetRV.layoutManager = layoutManager
                    internetRV.setHasFixedSize(true)
                    internetRV.adapter = internetRVAdapter
                }
            }
        }

    }

    override fun getItemCount(): Int = homeItems.size

    override fun getItemViewType(position: Int)= homeItems[position].first

    private inner class TopGreetingViewHolder(val binding: TopGreetingBinding):RecyclerView.ViewHolder(binding.root)

    private inner class ThirdPartySliderViewHolder(val binding: SingleImageSliderThirdPartyBinding):RecyclerView.ViewHolder(binding.root)

    private inner class RechargeViewHolder(val binding: RechargeBinding):RecyclerView.ViewHolder(binding.root)

    private inner class TotalBalanceViewHolder(val binding: TotalBalanceBinding):RecyclerView.ViewHolder(binding.root)

    private inner class ShortcutsViewHolder(val binding: AllShortcutItemsBinding):RecyclerView.ViewHolder(binding.root)

    private inner class EshopViewHolder(val binding: AllEshopItemsBinding):RecyclerView.ViewHolder(binding.root)

    private inner class InternetViewHolder(val binding: AllInternetItemsBinding):RecyclerView.ViewHolder(binding.root)

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }
}