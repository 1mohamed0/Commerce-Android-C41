package com.mis.route.e_commerce.domain.model

data class Cart(
    val totalPrice: Double = 0.0,
    val itemCount: Int,
    val products: List<Product>
)