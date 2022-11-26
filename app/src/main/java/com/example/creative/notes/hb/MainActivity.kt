package com.example.creative.notes.hb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)
    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return navController.navigateUp() || super.navigateUpTo(upIntent)
    }
}