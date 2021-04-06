package com.example.homeworksecond.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworksecond.MainActivity
import com.example.homeworksecond.R
import com.example.homeworksecond.ui.home.adapter.HomeFragmentRVAdapter
import com.example.homeworksecond.databinding.FragmentHomeBinding
import com.example.homeworksecond.model.*
import com.example.homeworksecond.ui.home.viewmodel.HomeFragmentViewModel
import com.example.homeworksecond.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
            homeFragmentRVAdapter.addData(it as ArrayList<SliderModel>)
        })
        viewModel.getShortcutList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addData(it as ArrayList<ShortcutModel>)
        })
        viewModel.getEshopItemList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addData(it as ArrayList<EshopItemModel>)
        })
        viewModel.getInternetItemList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addData(it as ArrayList<InternetItemModel>)
        })
        setUpParentArrayListObserver()
    }


    private fun setUpParentArrayListObserver(){
        viewModel.getParentArrayList().observe(viewLifecycleOwner, {
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { parentArrayList ->
                        homeFragmentRVAdapter.addData(parentArrayList as ArrayList<ParentItemModel>)
                        homeFragmentRVAdapter.notifyDataSetChanged()
                    }
                    binding.homeFragmentRV.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.homeFragmentRV.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
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