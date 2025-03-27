package com.mis.route.e_commerce.data.models.category

import com.google.gson.annotations.SerializedName
import com.mis.route.e_commerce.data.models.common.Metadata

data class CategoriesResponse(
    val results: Int? = null,
    val metadata: Metadata? = null,
    @SerializedName("data")
    val categories: List<CategoryDM>? = null,
)

