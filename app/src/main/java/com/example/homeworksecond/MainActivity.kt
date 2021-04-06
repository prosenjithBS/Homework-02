package com.example.homeworksecond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homeworksecond.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        setUpBottomNavigationView()

//        supportFragmentManager.commit {
//            val homeScreen= HomeFragment()
//            setReorderingAllowed(true)
//            add(R.id.fragmentContainerView,homeScreen)
//            //addToBackStack("all_movies")
//        }
    }

    private fun setUpBottomNavigationView() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setUpToolbar(){
        val toolbar = binding.homeToolbar
        setSupportActionBar(toolbar)
        toolbar.title="Banglalink"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
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