package com.mis.route.e_commerce.ui.activities.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mis.route.e_commerce.databinding.ActivityCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private var _binding: ActivityCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}