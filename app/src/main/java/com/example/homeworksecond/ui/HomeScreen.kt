package com.example.homeworksecond.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.MainActivity
import com.example.homeworksecond.R
import com.example.homeworksecond.adapter.HomeScreenRVAdapter
import com.example.homeworksecond.data.DataSource
import com.example.homeworksecond.databinding.HomeScreenBinding
import com.example.homeworksecond.model.ParentItemModel


class HomeScreen: Fragment() {
    private lateinit var binding:HomeScreenBinding
    private lateinit var homeScreenRecyclerView:RecyclerView
    private lateinit var homeScreenRVAdapter:HomeScreenRVAdapter
    private lateinit var homeScreenLayoutManager:RecyclerView.LayoutManager
    private lateinit var parentItemModelArrayList:ArrayList<ParentItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= HomeScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        parentItemModelArrayList=DataSource.parentArrayList
        setUpRecyclerView()
    }
    private fun setUpToolbar(){
        val toolbar = binding.homeToolbar
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.title="Banglalink"
    }

    private fun setUpRecyclerView(){
        homeScreenRecyclerView=binding.homeScreenRV
        homeScreenRecyclerView.setHasFixedSize(true)
        homeScreenLayoutManager = LinearLayoutManager(activity)
        homeScreenRVAdapter = context?.let { HomeScreenRVAdapter(it, parentItemModelArrayList) }!!
        homeScreenRecyclerView.layoutManager = homeScreenLayoutManager
        homeScreenRecyclerView.adapter = homeScreenRVAdapter
        homeScreenRVAdapter.notifyDataSetChanged()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                Log.d("menus", "Search icon clicked!")
                return true
            }
            R.id.toggle -> {
                Log.d("menus", "Toggle icon clicked!")
                return true
            }
            else -> {
                Log.d("menus", "Notification icon clicked!")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}