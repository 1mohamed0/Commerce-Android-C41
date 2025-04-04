package com.mis.route.e_commerce.data.models.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(

    @field:SerializedName("data")
    val data: CartDM? = null,

    @field:SerializedName("numOfCartItems")
    val numOfCartItems: Int? = null,

    @field:SerializedName("cartId")
    val cartId: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)