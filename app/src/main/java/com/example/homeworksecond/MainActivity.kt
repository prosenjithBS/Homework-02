package com.example.homeworksecond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.homeworksecond.databinding.ActivityMainBinding
import com.example.homeworksecond.ui.HomeScreen

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            val homeScreen= HomeScreen()
            setReorderingAllowed(true)
            add(R.id.fragmentContainerView,homeScreen)
            //addToBackStack("all_movies")
        }
    }
}