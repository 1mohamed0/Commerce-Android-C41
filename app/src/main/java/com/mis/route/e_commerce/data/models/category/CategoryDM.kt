package com.mis.route.e_commerce.data.models.category

import com.google.gson.annotations.SerializedName

data class CategoryDM(
    @SerializedName("_id")
    val id: String? = null,
    val name: String? = null,
    val slug: String? = null,
    val image: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val category: String? = null
)