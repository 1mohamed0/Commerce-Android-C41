package com.mis.route.e_commerce.data.repositories.cart_repository.data_sources.cart_remote_source

import com.mis.route.e_commerce.data.models.cart.CartResponse
import com.mis.route.e_commerce.domain.utils.ApiResult

interface CartRemoteDataSource {
    suspend fun addToCart(productId: String): ApiResult<CartResponse>
    suspend fun getCart(): ApiResult<CartResponse>
    suspend fun removeFromCart(productId: String): ApiResult<CartResponse>

    suspend fun updateProductQuantity(productId: String, quantity: Int): ApiResult<CartResponse>
}