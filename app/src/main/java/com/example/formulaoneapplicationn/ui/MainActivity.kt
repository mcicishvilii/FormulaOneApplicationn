package com.example.formulaoneapplicationn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formulaone.databinding.ActivityMainBinding
import com.example.formulaoneapplicationn.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        drawerListener()
    }//ajnsdjhasda



//    private fun drawerListener() {
//        binding?.drawer?.setOnTouchListener(object: Swipe(this@MainActivity) {
//            override fun onSwipeRight() {
//                startDrawer()
//            }
//        }
//        )
//    }
//
//
//    private fun startDrawer() {
//        binding?.drawer?.openDrawer(
//            GravityCompat.START, true
//        )
//    }
}