package com.noweto.nasaclone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.noweto.nasaclone.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_activity.*

//~~ Handle bottom nav and navigation component

@AndroidEntryPoint
class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setUpBottomNav()

    }

    private fun setUpBottomNav(){

        val navController = Navigation.findNavController(this, R.id.nav_host)
        bottom_nav.setupWithNavController(navController)

    }


}