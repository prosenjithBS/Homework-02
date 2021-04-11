package com.example.homeworksecond.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworksecond.ui.home.adapter.HomeFragmentRVAdapter
import com.example.homeworksecond.databinding.FragmentHomeBinding
import com.example.homeworksecond.model.*
import com.example.homeworksecond.ui.home.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var homeFragmentRVAdapter:HomeFragmentRVAdapter
    private lateinit var binding:FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpViewModel() {

        viewModel.getSliderModelArrayList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addHomeItems(it as ArrayList<SliderModel>,2)
        })

        viewModel.getShortcutList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addHomeItems(it as ArrayList<ShortcutModel>,5)

        })

        viewModel.getEshopItemList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addHomeItems(it as ArrayList<EshopItemModel>,6)
        })

        viewModel.getInternetItemList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addHomeItems(it as ArrayList<InternetItemModel>,7)
        })

    }

    private fun setUpRecyclerView(){
        homeFragmentRVAdapter = HomeFragmentRVAdapter()
        binding.homeFragmentRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeFragmentRVAdapter
        }
    }
}