package com.mis.route.e_commerce.data.mappers

import com.mis.route.e_commerce.data.models.cart.CartResponse
import com.mis.route.e_commerce.domain.model.Cart
import javax.inject.Inject

class CartMapper @Inject constructor(
    val productMapper: ProductMapper
) {
    fun fromCartResponse(cartResponse: CartResponse) {
        var cartEntries = cartResponse.data?.products ?: emptyList()
        var products = cartEntries.map { cartEntry ->
            var product = productMapper.fromDataModel(cartEntry!!.product!!)
            product.totalPriceInCart = cartEntry.price ?: 0.0
            product.totalItemsInCart = cartEntry.count ?: 0
            return@map product
        }
        var cart =
            Cart(
                totalPrice = cartResponse.data?.totalCartPrice ?: 0.0,
                itemCount = cartResponse.numOfCartItems ?: 0,
                products = products
            )
    }
}