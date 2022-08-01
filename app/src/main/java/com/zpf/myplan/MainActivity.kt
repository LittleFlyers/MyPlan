package com.zpf.myplan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zpf.myplan.databinding.ActivityMainBinding
import com.zpf.myplan.navigation.NavigationManager
import com.zpf.myplan.ui.widget.navigation.BottomNavigationMenuView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val supportFragmentManager = supportFragmentManager
        val navigationManager =
            NavigationManager(supportFragmentManager, R.id.nav_host_fragment_activity_main)
        val bottomNavigationView = findViewById<BottomNavigationMenuView>(R.id.nav_view)
        bottomNavigationView.setNavigationManager(navigationManager)
    }
}