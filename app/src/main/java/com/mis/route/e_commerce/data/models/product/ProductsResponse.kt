package com.mis.route.e_commerce.data.models.product

import com.google.gson.annotations.SerializedName
import com.mis.route.e_commerce.data.models.common.Metadata

data class ProductsResponse(
    val results: Int? = null,
    val metadata: Metadata? = null,
    @SerializedName("data")
    val products: List<ProductDM>? = null
)