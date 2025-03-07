package com.mis.route.e_commerce.ui.activities.product_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mis.route.e_commerce.databinding.ActivityProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}