package com.mis.route.e_commerce.data.models.product

import com.mis.route.e_commerce.data.models.category.CategoryDM


data class ProductDM(
    val sold: Int? = null,
    val images: List<String?>? = null,
    val quantity: Int? = null,
    val imageCover: String? = null,
    val description: String? = null,
    val title: String? = null,
    val ratingsQuantity: Int? = null,
    val ratingsAverage: Any? = null,
    val createdAt: String? = null,
    val price: Int? = null,
    val id: String? = null,
    val subcategory: List<CategoryDM?>? = null,
    val category: CategoryDM? = null,
    val brand: CategoryDM? = null,
    val slug: String? = null,
    val updatedAt: String? = null,
)