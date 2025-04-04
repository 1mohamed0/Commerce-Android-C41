package com.mis.route.e_commerce.data.repositories.cart_repository.data_sources.cart_remote_source

import com.mis.route.e_commerce.data.api.web_services.WebServices
import com.mis.route.e_commerce.data.models.cart.CartResponse
import com.mis.route.e_commerce.data.utils.safeApiCall
import com.mis.route.e_commerce.domain.utils.ApiResult
import javax.inject.Inject

class CartRemoteDataSourceImpl @Inject constructor(private val services: WebServices) :
    CartRemoteDataSource {
    override suspend fun addToCart(productId: String): ApiResult<CartResponse> =
        safeApiCall { services.addToCart(productId) }

    override suspend fun getCart(): ApiResult<CartResponse> =
        safeApiCall { services.getCart() }

    override suspend fun removeFromCart(productId: String): ApiResult<CartResponse> =
        safeApiCall { services.removeFromCart(productId) }

    override suspend fun updateProductQuantity(
        productId: String,
        quantity: Int
    ): ApiResult<CartResponse> =
        safeApiCall { services.updateProductQuantity(productId, quantity) }

}