package com.mis.route.e_commerce.ui.activities.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavController()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}