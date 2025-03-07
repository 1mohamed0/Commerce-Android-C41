package com.mis.route.e_commerce.ui.activities.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.ActivityHomeBinding
import com.mis.route.e_commerce.ui.activities.cart.CartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveNavController()
        initBottomNavView()
        binding.cartImageview.setOnClickListener { navigateToCart() }
    }

    private fun navigateToCart() {
        startActivity(Intent(this, CartActivity::class.java))
    }

    private fun retrieveNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initBottomNavView() {
        // let the icon selector determine the tint
        binding.bottomNavigation.itemIconTintList = null
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}