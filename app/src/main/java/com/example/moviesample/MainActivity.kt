package com.example.moviesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottonnav)
        bottomNavigationView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    val directions = NavDirections.actionGlobalHomeFragment()
                    directions.filterFavorites = false
                    findNavController(this, R.id.nav_host_fragment).navigate(directions)
                    true
                }

                R.id.favoritesFragment -> {
                    val directions = NavDirections.actionGlobalHomeFragment()
                    directions.filterFavorites = true
                    findNavController(this, R.id.nav_host_fragment).navigate(directions)
                    true
                }

                else -> true
            }
        }

    }
}