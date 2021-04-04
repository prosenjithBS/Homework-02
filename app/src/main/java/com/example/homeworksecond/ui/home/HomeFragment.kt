package com.example.homeworksecond.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworksecond.MainActivity
import com.example.homeworksecond.R
import com.example.homeworksecond.ui.home.adapter.HomeFragmentRVAdapter
import com.example.homeworksecond.databinding.FragmentHomeBinding
import com.example.homeworksecond.model.*
import com.example.homeworksecond.ui.home.viewmodel.HomeFragmentViewModel
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
        setUpToolbar()
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
        viewModel.getParentArrayList().observe(viewLifecycleOwner, {
            homeFragmentRVAdapter.addData(it as ArrayList<ParentItemModel>)
        })

    }

    private fun setUpToolbar(){
        val toolbar = binding.homeToolbar
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.title="Banglalink"
    }

    private fun setUpRecyclerView(){
        homeFragmentRVAdapter = HomeFragmentRVAdapter()
        homeFragmentRVAdapter.notifyDataSetChanged()
        binding.homeFragmentRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeFragmentRVAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                Log.d("menus", "Search icon clicked!")
                true
            }
            R.id.toggle -> {
                Log.d("menus", "Toggle icon clicked!")
                true
            }
            else -> {
                Log.d("menus", "Notification icon clicked!")
                true
            }
        }
    }
}