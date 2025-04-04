package com.mis.route.e_commerce.domain.repositories

import com.mis.route.e_commerce.domain.model.Cart
import com.mis.route.e_commerce.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(): Flow<ApiResult<Cart>>
    fun addToCart(productId: String): Flow<ApiResult<Cart>>
    fun removeFromCart(productId: String): Flow<ApiResult<Cart>>
    fun updateCartQuantity(productId: String, quantity: Int): Flow<ApiResult<Cart>>
}