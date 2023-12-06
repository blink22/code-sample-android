package com.blink22.sample.topKotlins.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.blink22.sample.topKotlins.R
import com.blink22.sample.topKotlins.databinding.ActivityMainBinding
import com.blink22.sample.topKotlins.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        setupNavHostFragment()
    }

    /**
     * Setup single activity application's navigation.
     * */
    private fun setupNavHostFragment() {
        val navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        setupActionBarWithNavController(navController)
    }

}