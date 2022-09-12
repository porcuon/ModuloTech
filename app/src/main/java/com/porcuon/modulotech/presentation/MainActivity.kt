package com.porcuon.modulotech.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.porcuon.modulotech.R

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val shouldShowBottomNavView = destination.id == R.id.devicesFragment || destination.id == R.id.profileFragment

        bottomNavView.visibility = when (shouldShowBottomNavView) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun setupNavController() {
        val navController: NavController = findNavController(R.id.fragment_container_view)

        bottomNavView = findViewById(R.id.bottom_nav_view)
        bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }
}