package com.mis.route.e_commerce.data.models.cart

import com.google.gson.annotations.SerializedName
import com.mis.route.e_commerce.data.models.product.ProductDM

data class CartEntry(

    @field:SerializedName("product")
    val product: ProductDM? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null
)