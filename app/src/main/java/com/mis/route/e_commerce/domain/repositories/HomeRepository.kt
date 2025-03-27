package com.mis.route.e_commerce.domain.repositories

import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.domain.model.Product
import com.mis.route.e_commerce.domain.utils.ApiResult

interface HomeRepository {
    suspend fun getCategories(): ApiResult<List<Category>>
    suspend fun getSubCategories(categoryId: String): ApiResult<List<Category>>
    suspend fun getProducts(): ApiResult<List<Product>>

}