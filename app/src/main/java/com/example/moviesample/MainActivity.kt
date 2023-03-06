package com.example.moviesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    true
                }
                R.id.favoritesFragment -> {
                    true
                }
                else -> true
            }
        }

    }
}